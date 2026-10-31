package com.lendinglibrary.libraryapp.repository;

import com.lendinglibrary.libraryapp.dto.BookReadOnlyDTO;
import com.lendinglibrary.libraryapp.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    Optional<Book> findByIsbn(String isbn);

    // Αναζήτηση σε τίτλο ή συγγραφέα (ignoring case)
    List<BookReadOnlyDTO> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);

    // Εύρεση ανά κατηγορία
    List<Book> findByCategoryIgnoreCase(String category);


}
