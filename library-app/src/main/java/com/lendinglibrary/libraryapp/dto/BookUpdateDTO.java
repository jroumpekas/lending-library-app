package com.lendinglibrary.libraryapp.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record BookUpdateDTO(
        @Size(max = 300, message = "Book's name must be up to 300 characters")
        String title,

        @Size(max = 100, message = "Author's name must be up to 100 characters")
        String author,

        String isbn,

        @Size(max = 100, message = "Category must be up to 100 characters")
        String category,

        @Size(max = 100, message = "Description must be up to 100 characters")
        String description
) {
}