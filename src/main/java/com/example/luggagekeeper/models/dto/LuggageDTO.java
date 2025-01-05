package com.example.luggagekeeper.models.dto;

import com.example.luggagekeeper.models.enumerations.LuggageType;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LuggageDTO {
    private LuggageType luggageType;

    private Double weightLimit;

    private Double size;

    private Boolean availability;

    public LuggageDTO(LuggageType luggageType, Double weightLimit, Double size, Boolean availability) {
        this.luggageType = luggageType;
        this.weightLimit = weightLimit;
        this.size = size;
        this.availability = availability;
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
