package com.lendinglibrary.libraryapp.repository;

import com.lendinglibrary.libraryapp.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {

    // Μέθοδος για να βρίσκουμε όλα τα αντίτυπα ενός συγκεκριμένου βιβλίου
    List<BookCopy> findByBookId(UUID bookId);

    // Αν θες να βρίσκεις π.χ. μόνο τα διαθέσιμα αντίτυπα ενός βιβλίου
    // List<BookCopy> findByBookIdAndAvailableTrue(UUID bookId);
}