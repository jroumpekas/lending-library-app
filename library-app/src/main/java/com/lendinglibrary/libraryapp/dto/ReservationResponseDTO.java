package com.lendinglibrary.libraryapp.dto;

import java.time.Instant;
import java.util.UUID;

public record ReservationResponseDTO(
        UUID id,
        String bookTitle,
        String username,
        Instant reservationDate,
        Instant dueDate,
        String status
) {}