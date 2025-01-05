package com.example.luggagekeeper.services.implementation;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.dto.LocationDTO;
import com.example.luggagekeeper.models.dto.OrderDTO;
import com.example.luggagekeeper.repository.LocationRepository;
import com.example.luggagekeeper.services.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImplementation implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImplementation(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> listAllLocations() {
        return this.locationRepository.findAll();
    }

    @Override
    public Page<Location> findAllWithPagination(Pageable pageable) {
        return this.locationRepository.findAll(pageable);
    }

    @Override
    public Optional<Location> findLocationsById(Long id) {
        return this.locationRepository.findById(id);
    }

    @Override
    public Optional<Location> createLocation(LocationDTO locationDTO) {
        return Optional.of(this.locationRepository.save(new Location(
                locationDTO.getName(),
                locationDTO.getAddress(),
                locationDTO.getCapacity()
        )));
    }

    @Override
    public Optional<Location> editLocation(Long id, LocationDTO locationDTO) {
        Location location=this.locationRepository.findById(id).get();
        location.setName(locationDTO.getName());
        location.setAddress(locationDTO.getAddress());
        location.setCapacity(locationDTO.getCapacity());
        return Optional.empty();
    }

    @Override
    public void deleteLocation(Long id) {
        this.locationRepository.deleteById(id);

    }
}
