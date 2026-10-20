package com.lendinglibrary.libraryapp.service;

import com.lendinglibrary.libraryapp.dto.BookInsertDTO;
import com.lendinglibrary.libraryapp.dto.BookReadOnlyDTO;
import com.lendinglibrary.libraryapp.dto.BookUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService {

    List<BookReadOnlyDTO> getAllBooks();

    BookReadOnlyDTO getBookById(UUID uuid);

    BookReadOnlyDTO createBook(BookInsertDTO dto);

    BookReadOnlyDTO updateBook(UUID uuid, BookUpdateDTO dto);

    void deleteBook(UUID uuid);

    Page<BookReadOnlyDTO> getPaginatedBooks(Pageable pageable);
}
