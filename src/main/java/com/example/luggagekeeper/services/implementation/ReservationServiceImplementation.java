package com.example.luggagekeeper.services.implementation;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.Luggage;
import com.example.luggagekeeper.models.Reservation;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.dto.ReservationDTO;
import com.example.luggagekeeper.models.enumerations.OrderStatus;
import com.example.luggagekeeper.repository.ReservationRepository;
import com.example.luggagekeeper.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReservationServiceImplementation implements ReservationService {


    private final ReservationRepository reservationRepository;

    public ReservationServiceImplementation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Page<Reservation> findAllWithPagination(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation createReservation(OrderStatus orderStatus, Location location, LocalDateTime startDate, java.time.LocalDateTime endDate, Luggage luggage, User user){
        Reservation reservation = new Reservation();
        reservation.setOrderStatus(orderStatus);
        reservation.setLocation(location);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setUser(user);
        reservation.setLuggage(luggage);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, OrderStatus orderStatus, Location location, LocalDateTime startDate, LocalDateTime endDate, Luggage luggage, User user) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setOrderStatus(orderStatus);
            reservation.setLocation(location);
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setUser(user);
            reservation.setLuggage(luggage);
            return reservationRepository.save(reservation);
        }
        return null; // Handle this appropriately in a real-world scenario
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Page<Reservation> getReservationsByUserId(Long userId, Pageable pageable) {
        return reservationRepository.findReservationsByUserId(userId, pageable);
    }
}
