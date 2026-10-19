package com.lendinglibrary.libraryapp.models;

import com.lendinglibrary.libraryapp.models.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "reservations") // Πληθυντικός όπως τα υπόλοιπα tables
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation extends AbstractEntity { // 1. Extends AbstractEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id", nullable = false) // 2. book_copy_id
    private BookCopy bookCopy;

    @Column(name = "reserved_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private Instant reservedAt;

    @Column(name = "due_date", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private Instant dueDate;

    @Column(name = "returned_at", columnDefinition = "TIMESTAMPTZ")
    private Instant returnedAt; // NULL όσο είναι δανεισμένο

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    @Builder.Default
    private ReservationStatus status = ReservationStatus.ACTIVE;
}