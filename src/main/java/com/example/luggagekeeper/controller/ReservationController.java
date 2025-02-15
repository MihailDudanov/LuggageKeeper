package com.example.luggagekeeper.controller;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.Luggage;
import com.example.luggagekeeper.models.Reservation;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.dto.ReservationDTO;
import com.example.luggagekeeper.services.LocationService;
import com.example.luggagekeeper.services.LuggageService;
import com.example.luggagekeeper.services.ReservationService;
import com.example.luggagekeeper.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@CrossOrigin("https://localhost:3000")
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final LuggageService luggageService;
    private final UserService userService;
    private final LocationService locationService;

    public ReservationController(ReservationService reservationService, LuggageService luggageService, UserService userService, LocationService locationService) {
        this.reservationService = reservationService;
        this.luggageService = luggageService;
        this.userService = userService;
        this.locationService = locationService;
    }


    @GetMapping
    public ResponseEntity<Page<Reservation>> getAllReservations(Pageable pageable) {
        return ResponseEntity.ok(reservationService.findAllWithPagination(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return reservation.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            Long luggageId = Long.parseLong(reservationDTO.getLuggageID());
            Long locationId = Long.parseLong(reservationDTO.getLocation());

            Luggage luggage = luggageService.findLuggagesById(luggageId)
                    .orElseThrow(() -> new IllegalArgumentException("Luggage not found"));

            Location location = locationService.findLocationsById(locationId)
                    .orElseThrow(() -> new IllegalArgumentException("Location not found"));

            // Fetch the authenticated user
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
            }

            User loggedInUser = userService.findByUsername(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Create the reservation
            Reservation reservation = reservationService.createReservation(
                    reservationDTO.getOrderStatus(), location,
                    Instant.parse(reservationDTO.getStartDate())
                            .atZone(ZoneId.systemDefault()) // Converts to system's default time zone
                            .toLocalDateTime(),
                    Instant.parse(reservationDTO.getEndDate())
                            .atZone(ZoneId.systemDefault()) // Converts to system's default time zone
                            .toLocalDateTime(), luggage, loggedInUser);

            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
        Luggage luggage = luggageService.findLuggagesById(Long.getLong(reservationDTO.getLuggageID())).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findByUsername(authentication.getName()).get();
        Location location = locationService.findLocationsById(Long.getLong(reservationDTO.getLocation())).get();
        Reservation updatedReservation = reservationService.updateReservation(id, reservationDTO.getOrderStatus(), location, LocalDateTime.parse(reservationDTO.getStartDate()),
                LocalDateTime.parse(reservationDTO.getEndDate()), luggage, loggedInUser);

        if (updatedReservation != null) {
            return ResponseEntity.ok(updatedReservation);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Reservation>> getReservationsByUserId(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId, pageable));
    }
}