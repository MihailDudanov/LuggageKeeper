package com.example.luggagekeeper.services;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.Luggage;
import com.example.luggagekeeper.models.dto.LocationDTO;
import com.example.luggagekeeper.models.dto.LuggageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LuggageService {
    List<Luggage> listAllLuggages();
    Page<Luggage> findAllWithPagination(Pageable pageable);
    Optional<Luggage> findLuggagesById(Long id);
    Optional<Luggage> createLuggage(LuggageDTO luggageDTO);
    Optional<Luggage> editLuggage(Long id, LuggageDTO luggageDTO);
    void deleteLuggage(Long id);
}
