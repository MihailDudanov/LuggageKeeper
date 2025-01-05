package com.example.luggagekeeper.models;

import com.example.luggagekeeper.models.enumerations.LuggageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "luggage")
public class Luggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "luggage_type")
    private LuggageType luggageType;

    @Column(name = "weight_limit")
    private Double weightLimit;

    @Column(name = "size")
    private Double size;

    @Column(name = "availability")
    private Boolean availability;

    public Luggage(LuggageType luggageType, Double weightLimit, Double size, Boolean availability) {
        this.luggageType = luggageType;
        this.weightLimit = weightLimit;
        this.size = size;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LuggageType getLuggageType() {
        return luggageType;
    }

    public void setLuggageType(LuggageType luggageType) {
        this.luggageType = luggageType;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
