package com.lendinglibrary.libraryapp.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ReservationRequestDTO(
        @NotNull(message = "Το ID του βιβλίου είναι υποχρεωτικό")
        UUID bookId
) {}