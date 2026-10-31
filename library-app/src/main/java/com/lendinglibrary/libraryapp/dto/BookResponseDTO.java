package com.lendinglibrary.libraryapp.dto;

import com.lendinglibrary.libraryapp.models.Book;

public record BookResponseDTO(

        java.util.UUID id,
        String title,
        String author,
        String isbn,
        String category,
        String description
) {
    // Utility method για εύκολη μετατροπή από Entity σε Response DTO
    public static BookResponseDTO fromEntity(Book book) {
        return new BookResponseDTO(
                book.getId(), // Ή book.getId() ανάλογα με το πώς το έχεις ορίσει στο AbstractEntity
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getCategory(),
                book.getDescription()
        );

    }

}

