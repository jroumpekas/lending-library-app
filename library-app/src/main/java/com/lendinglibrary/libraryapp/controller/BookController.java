package com.lendinglibrary.libraryapp.controller;

import com.lendinglibrary.libraryapp.dto.BookInsertDTO;
import com.lendinglibrary.libraryapp.dto.BookReadOnlyDTO;
import com.lendinglibrary.libraryapp.dto.BookUpdateDTO;
import com.lendinglibrary.libraryapp.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 1. Επιστρέφει ΟΛΑ τα βιβλία
    @GetMapping
    public ResponseEntity<List<BookReadOnlyDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // 2. Επιστρέφει Βιβλίο με βάση το UUID
    @GetMapping("/{id}")
    public ResponseEntity<BookReadOnlyDTO> getBookById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // 3. Επιστρέφει τα βιβλία με Σελιδοποίηση (Paged)
    @GetMapping("/paged")
    public ResponseEntity<Page<BookReadOnlyDTO>> getPaginatedBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "title") String sort // Διορθώθηκε από "name" σε "title"
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<BookReadOnlyDTO> books = bookService.getPaginatedBooks(pageable);
        return ResponseEntity.ok(books);
    }

    // 4. Δημιουργία βιβλίου (με BookInsertDTO)
    @PostMapping
    public ResponseEntity<BookReadOnlyDTO> createBook(
            @Valid @RequestBody BookInsertDTO dto
    ) {
        BookReadOnlyDTO book = bookService.createBook(dto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // 5. Ενημέρωση βιβλίου (με BookUpdateDTO)
    @PutMapping("/{id}")
    public ResponseEntity<BookReadOnlyDTO> updateBook(
            @PathVariable UUID id,
            @Valid @RequestBody BookUpdateDTO dto
    ) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    // 6. Διαγραφή βιβλίου
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}