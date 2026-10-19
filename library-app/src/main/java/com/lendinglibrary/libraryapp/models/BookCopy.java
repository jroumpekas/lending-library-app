package com.lendinglibrary.libraryapp.models;

import com.lendinglibrary.libraryapp.models.enums.CopyStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_copies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCopy extends AbstractEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private CopyStatus status = CopyStatus.AVAILABLE;
}
