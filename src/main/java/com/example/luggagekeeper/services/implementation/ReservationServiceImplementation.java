package com.example.luggagekeeper.services.implementation;

import com.example.luggagekeeper.models.Reservation;
import com.example.luggagekeeper.models.dto.ReservationDTO;
import com.example.luggagekeeper.repository.ReservationRepository;
import com.example.luggagekeeper.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImplementation implements ReservationService {


    private ReservationRepository reservationRepository;

    @Override
    public Page<Reservation> findAllWithPagination(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setOrderStatus(reservationDTO.getOrderStatus());
        reservation.setUser(reservationDTO.getUser());
        reservation.setLocation(reservationDTO.getLocation());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, ReservationDTO reservationDTO) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setOrderStatus(reservationDTO.getOrderStatus());
            reservation.setUser(reservationDTO.getUser());
            reservation.setLocation(reservationDTO.getLocation());
            reservation.setStartDate(reservationDTO.getStartDate());
            reservation.setEndDate(reservationDTO.getEndDate());
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
