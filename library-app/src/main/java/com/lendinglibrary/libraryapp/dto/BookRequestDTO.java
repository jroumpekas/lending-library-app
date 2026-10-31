package com.lendinglibrary.libraryapp.dto;

public record BookRequestDTO(
        String title,
        String author,
        String isbn,
        String category,
        String description
) {
}
