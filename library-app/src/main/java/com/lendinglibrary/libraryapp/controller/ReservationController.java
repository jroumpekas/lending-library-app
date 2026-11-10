package com.lendinglibrary.libraryapp.controller;

import com.lendinglibrary.libraryapp.dto.AIRecommendationDTO;
import com.lendinglibrary.libraryapp.dto.ReservationRequestDTO;
import com.lendinglibrary.libraryapp.dto.ReservationResponseDTO;
import com.lendinglibrary.libraryapp.models.Reservation;
import com.lendinglibrary.libraryapp.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RestClient aiRestClient; // 1. Προσθήκη πεδίου για τον RestClient

    // 2. Constructor Injection για Spring Boot (autowired αυτόματα)
    public ReservationController(ReservationService reservationService, RestClient aiRestClient) {
        this.reservationService = reservationService;
        this.aiRestClient = aiRestClient;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO requestDTO, Principal principal) {
        String username = principal.getName();

        Reservation reservation = reservationService.createReservation(username, requestDTO.bookId());

        ReservationResponseDTO responseDTO = new ReservationResponseDTO(
                reservation.getId(),
                reservation.getBook().getTitle(),
                reservation.getUser().getUsername(),
                reservation.getReservedAt(),
                reservation.getDueDate(),
                reservation.getStatus().name()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/my")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ReservationResponseDTO>> getMyReservations(Principal principal) {
        String username = principal.getName();
        List<Reservation> reservations = reservationService.getReservationsByUser(username);

        List<ReservationResponseDTO> responseDTOs = reservations.stream().map(r -> new ReservationResponseDTO(
                r.getId(),
                r.getBook().getTitle(),
                r.getUser().getUsername(),
                r.getReservedAt(),
                r.getDueDate(),
                r.getStatus().name()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();

        List<ReservationResponseDTO> responseDTOs = reservations.stream().map(r -> new ReservationResponseDTO(
                r.getId(),
                r.getBook().getTitle(),
                r.getUser().getUsername(),
                r.getReservedAt(),
                r.getDueDate(),
                r.getStatus().name()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/ai/recommendations")
    public ResponseEntity<List<AIRecommendationDTO>> getAIRecommendations(Principal principal) {
        String username = principal.getName();

        try {
            // 3. Χρήση του κεντρικού aiRestClient που έχει ρυθμισμένα timeouts
            AIRecommendationDTO[] recommendations = aiRestClient.get()
                    .uri("/api/ai/recommendations/" + username)
                    .retrieve()
                    .body(AIRecommendationDTO[].class);

            return ResponseEntity.ok(recommendations != null ? List.of(recommendations) : List.of());
        } catch (Exception e) {
            // Αν το FastAPI καθυστερήσει πάνω από 3 δευτερόλεπτα ή είναι κλειστό,
            // πιάνουμε το exception εδώ και γυρνάμε κενή λίστα αντί να κολλήσει το UI.
            return ResponseEntity.ok(List.of());
        }
    }
}