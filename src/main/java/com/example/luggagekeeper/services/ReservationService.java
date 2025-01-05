package com.example.luggagekeeper.services;

import com.example.luggagekeeper.models.Reservation;
import com.example.luggagekeeper.models.dto.ReservationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReservationService {
    Page<Reservation> findAllWithPagination(Pageable pageable);
    Optional<Reservation> getReservationById(Long id);
    Reservation createReservation(ReservationDTO reservationDTO);
    Reservation updateReservation(Long id, ReservationDTO reservationDTO);
    void deleteReservation(Long id);
    Page<Reservation> getReservationsByUserId(Long userId, Pageable pageable);
}
