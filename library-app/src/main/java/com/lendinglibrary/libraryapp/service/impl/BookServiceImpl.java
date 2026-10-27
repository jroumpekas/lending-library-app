package com.lendinglibrary.libraryapp.service.impl;

import com.lendinglibrary.libraryapp.dto.BookInsertDTO;
import com.lendinglibrary.libraryapp.dto.BookReadOnlyDTO;
import com.lendinglibrary.libraryapp.dto.BookUpdateDTO;
import com.lendinglibrary.libraryapp.exception.custom.ResourceNotFoundException;
import com.lendinglibrary.libraryapp.mapper.BookMapper;
import com.lendinglibrary.libraryapp.models.Book;
import com.lendinglibrary.libraryapp.repository.BookRepository;
import com.lendinglibrary.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookReadOnlyDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toReadOnlyDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookReadOnlyDTO getBookById(UUID uuid) {
        Book book = bookRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + uuid + " was not found"));

        return bookMapper.toReadOnlyDTO(book);
    }

    @Override
    public BookReadOnlyDTO createBook(BookInsertDTO dto) {
        Book book = bookMapper.toEntity(dto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toReadOnlyDTO(savedBook);
    }

    @Override
    public BookReadOnlyDTO updateBook(UUID uuid, BookUpdateDTO dto) {
        Book book = bookRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + uuid + " was not found"));

        bookMapper.updateEntityFromDTO(dto, book);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toReadOnlyDTO(updatedBook);
    }

    @Override
    public void deleteBook(UUID uuid) {
        Book book = bookRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + uuid + " was not found"));
        bookRepository.delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookReadOnlyDTO> getPaginatedBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toReadOnlyDTO);
    }
}