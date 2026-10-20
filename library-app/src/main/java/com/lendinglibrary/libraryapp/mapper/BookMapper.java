package com.lendinglibrary.libraryapp.mapper;

import com.lendinglibrary.libraryapp.dto.BookInsertDTO;
import com.lendinglibrary.libraryapp.dto.BookReadOnlyDTO;
import com.lendinglibrary.libraryapp.dto.BookUpdateDTO;
import com.lendinglibrary.libraryapp.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    // Μετατροπή από Book Entity σε BookReadOnlyDTO
    public BookReadOnlyDTO toReadOnlyDTO(Book book) {
        if (book == null) {
            return null;
        }
        return BookReadOnlyDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .category(book.getCategory())
                .description(book.getDescription())
                .build();
    }

    // Μετατροπή από BookInsertDTO σε Book Entity (για δημιουργία)
    public Book toEntity(BookInsertDTO insertDTO) {
        if (insertDTO == null) {
            return null;
        }
        return Book.builder()
                .title(insertDTO.title())
                .author(insertDTO.author())
                .isbn(insertDTO.isbn())
                .category(insertDTO.category())
                .description(insertDTO.description())
                .build();
    }

    // Ενημέρωση υφιστάμενου Book Entity από BookUpdateDTO (Partial/Full update)
    public void updateEntityFromDTO(BookUpdateDTO updateDTO, Book book) {
        if (updateDTO == null || book == null) {
            return;
        }
        if (updateDTO.title() != null) {
            book.setTitle(updateDTO.title());
        }
        if (updateDTO.author() != null) {
            book.setAuthor(updateDTO.author());
        }
        if (updateDTO.isbn() != null) {
            book.setIsbn(updateDTO.isbn());
        }
        if (updateDTO.category() != null) {
            book.setCategory(updateDTO.category());
        }
        if (updateDTO.description() != null) {
            book.setDescription(updateDTO.description());

        }
    }
}