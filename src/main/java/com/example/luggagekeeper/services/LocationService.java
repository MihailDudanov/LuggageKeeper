package com.example.luggagekeeper.services;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.Order;
import com.example.luggagekeeper.models.dto.LocationDTO;
import com.example.luggagekeeper.models.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> listAllLocations();
    Page<Location> findAllWithPagination(Pageable pageable);
    Optional<Location> findLocationsById(Long id);
    Optional<Location> createLocation(LocationDTO locationDTO);
    Optional<Location> editLocation(Long id, LocationDTO locationDTO);
    void deleteLocation(Long id);
}
