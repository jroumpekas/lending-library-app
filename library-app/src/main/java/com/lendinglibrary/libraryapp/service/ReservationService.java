package com.lendinglibrary.libraryapp.service;

import com.lendinglibrary.libraryapp.models.Reservation;
import java.util.List;
import java.util.UUID;

public interface ReservationService {
    Reservation createReservation(String username, UUID bookId);
    List<Reservation> getReservationsByUser(String username);
    List<Reservation> getAllReservations();
}