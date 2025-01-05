package com.example.luggagekeeper.repository;

import com.example.luggagekeeper.models.Order;
import com.example.luggagekeeper.models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Page<Reservation> findAll(Pageable pageable);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
    Page<Reservation> findReservationsByUserId(Long userId, Pageable pageable);


}
