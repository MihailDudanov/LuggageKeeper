package com.example.luggagekeeper.controller;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.Luggage;
import com.example.luggagekeeper.models.dto.LocationDTO;
import com.example.luggagekeeper.models.dto.LuggageDTO;
import com.example.luggagekeeper.services.LuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://localhost:3000")
@RequestMapping("/api/luggage")
public class LuggageController {
    @Autowired
    private  LuggageService luggageService;

    @GetMapping
    public ResponseEntity<List<Luggage>> showAllLuggages(){
        List<Luggage>luggages = this.luggageService.listAllLuggages();
        if(!luggages.isEmpty()){
            return ResponseEntity.ok(luggages);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Luggage> save(@RequestBody LuggageDTO luggageDTO){
        return this.luggageService.createLuggage(luggageDTO)
                .map(luggage -> ResponseEntity.ok().body(luggage))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Luggage> save(@PathVariable Long id, @RequestBody LuggageDTO luggageDTO){
        return this.luggageService.editLuggage(id,luggageDTO)
                .map(luggage -> ResponseEntity.ok().body(luggage))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.luggageService.deleteLuggage(id);
        if(this.luggageService.findLuggagesById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
