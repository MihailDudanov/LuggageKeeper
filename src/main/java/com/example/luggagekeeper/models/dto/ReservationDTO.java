package com.example.luggagekeeper.models.dto;

import com.example.luggagekeeper.models.Location;
import com.example.luggagekeeper.models.Luggage;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.enumerations.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class ReservationDTO {

    private OrderStatus orderStatus;
    private String location;
    private String startDate;
    private String endDate;
    private String luggageID;
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLuggageID() {
        return luggageID;
    }

    public void setLuggageID(String luggageID) {
        this.luggageID = luggageID;
    }
}
