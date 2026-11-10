package com.lendinglibrary.libraryapp.repository;

import com.lendinglibrary.libraryapp.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByUserUsername(String username);
    List<Reservation> findByBookId(UUID bookId);
}