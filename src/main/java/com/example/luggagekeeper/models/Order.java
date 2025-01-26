
package com.example.luggagekeeper.models;

import com.example.luggagekeeper.models.enumerations.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "re_reservation")
    private Reservation reservation;

    @Column(name = "total_price")
    private Double total_price;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "order_date")
    private Date orderDate;

    public Order(Reservation reservation, Double total_price, OrderStatus orderStatus, Date orderDate) {
        this.reservation = reservation;
        this.total_price = total_price;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Order() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
