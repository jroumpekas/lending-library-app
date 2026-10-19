package com.lendinglibrary.libraryapp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends AbstractEntity {

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 500, name = "author")
    private String author;

    @Column(name = "isbn",nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(length = 100)
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;
}
