package com.lendinglibrary.libraryapp.service.impl;

import com.lendinglibrary.libraryapp.models.Book;
import com.lendinglibrary.libraryapp.models.Reservation;
import com.lendinglibrary.libraryapp.models.User;
import com.lendinglibrary.libraryapp.models.enums.ReservationStatus;
import com.lendinglibrary.libraryapp.repository.BookRepository; // <-- Εισαγωγή του BookRepository
import com.lendinglibrary.libraryapp.repository.ReservationRepository;
import com.lendinglibrary.libraryapp.repository.UserRepository;
import com.lendinglibrary.libraryapp.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository; // <-- Χρησιμοποιούμε το BookRepository αντί για BookCopyRepository
    private final UserRepository userRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  BookRepository bookRepository, // <-- Προσθήκη στον constructor
                                  UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Reservation createReservation(String username, UUID bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Ο χρήστης δεν βρέθηκε"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Το βιβλίο δεν βρέθηκε"));

        Reservation reservation = Reservation.builder()
                .user(user)
                .book(book) // Συνδέουμε απευθείας το Book
                .reservedAt(Instant.now())
                .dueDate(Instant.now().plus(14, ChronoUnit.DAYS))
                .status(ReservationStatus.ACTIVE)
                .build();

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByUser(String username) {
        return reservationRepository.findByUserUsername(username);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}