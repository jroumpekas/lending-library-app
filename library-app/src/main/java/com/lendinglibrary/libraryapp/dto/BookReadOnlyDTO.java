package com.lendinglibrary.libraryapp.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public record BookReadOnlyDTO(
        UUID id,
        String title,
        String author,
        String isbn,
        String category,
        String description
) {
}