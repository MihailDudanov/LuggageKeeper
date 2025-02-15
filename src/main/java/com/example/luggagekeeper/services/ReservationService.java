package com.example.luggagekeeper.services;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.Luggage;
import com.example.luggagekeeper.models.Reservation;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.dto.ReservationDTO;
import com.example.luggagekeeper.models.enumerations.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationService {
    Page<Reservation> findAllWithPagination(Pageable pageable);
    Optional<Reservation> getReservationById(Long id);
    Reservation createReservation(OrderStatus orderStatus, Location location, LocalDateTime startDate, LocalDateTime endDate, Luggage luggage, User user);
    Reservation updateReservation(Long id, OrderStatus orderStatus, Location location, LocalDateTime startDate, LocalDateTime endDate, Luggage luggage, User user);
    void deleteReservation(Long id);
    Page<Reservation> getReservationsByUserId(Long userId, Pageable pageable);
}
