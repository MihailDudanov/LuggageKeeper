package com.example.luggagekeeper.services.implementation;

import com.example.luggagekeeper.models.Luggage;
import com.example.luggagekeeper.models.dto.LuggageDTO;
import com.example.luggagekeeper.repository.LuggageRepository;
import com.example.luggagekeeper.services.LuggageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LuggageServiceImplementation implements LuggageService {
    private final LuggageRepository luggageRepository;

    public LuggageServiceImplementation(LuggageRepository luggageRepository) {
        this.luggageRepository = luggageRepository;
    }

    @Override
    public List<Luggage> listAllLuggages() {
        return this.luggageRepository.findAll();
    }

    @Override
    public Page<Luggage> findAllWithPagination(Pageable pageable) {
        return this.luggageRepository.findAll(pageable);
    }

    @Override
    public Optional<Luggage> findLuggagesById(Long id) {
        return this.luggageRepository.findById(id);
    }

    @Override
    public Optional<Luggage> createLuggage(LuggageDTO luggageDTO) {
        return Optional.of(this.luggageRepository.save(new Luggage(
                luggageDTO.getLuggageType(),
                luggageDTO.getWeightLimit(),
                luggageDTO.getSize(),
                luggageDTO.getAvailability()
        )));
    }

    @Override
    public Optional<Luggage> editLuggage(Long id, LuggageDTO luggageDTO) {
        Luggage luggage=this.luggageRepository.findById(id).get();
        luggage.setLuggageType(luggageDTO.getLuggageType());
        luggage.setWeightLimit(luggageDTO.getWeightLimit());
        luggage.setSize(luggageDTO.getSize());
        luggage.setAvailability(luggageDTO.getAvailability().booleanValue());
        return Optional.empty();
    }

    @Override
    public void deleteLuggage(Long id) {
        this.luggageRepository.deleteById(id);
    }
}
