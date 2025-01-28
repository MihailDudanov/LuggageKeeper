package com.example.luggagekeeper.controller;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.Order;
import com.example.luggagekeeper.models.dto.LocationDTO;
import com.example.luggagekeeper.models.dto.OrderDTO;
import com.example.luggagekeeper.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://localhost:3000")
@RequestMapping("/api/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
  @GetMapping
    public ResponseEntity<List<Location>> showAllLocations(){
        List<Location>locations = this.locationService.listAllLocations();
        if(!locations.isEmpty()){
            return ResponseEntity.ok(locations);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Location> save(@RequestBody LocationDTO locationDTO){
        return this.locationService.createLocation(locationDTO)
                .map(location -> ResponseEntity.ok().body(location))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Location> save(@PathVariable Long id, @RequestBody LocationDTO locationDTO){
        return this.locationService.editLocation(id,locationDTO)
                .map(location -> ResponseEntity.ok().body(location))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.locationService.deleteLocation(id);
        if(this.locationService.findLocationsById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
