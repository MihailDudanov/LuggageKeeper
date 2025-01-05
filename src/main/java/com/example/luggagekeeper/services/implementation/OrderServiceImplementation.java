package com.example.luggagekeeper.services.implementation;

import com.example.luggagekeeper.models.Order;
import com.example.luggagekeeper.models.Reservation;
import com.example.luggagekeeper.models.dto.OrderDTO;
import com.example.luggagekeeper.models.enumerations.OrderStatus;
import com.example.luggagekeeper.repository.OrderRepository;
import com.example.luggagekeeper.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImplementation(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> listAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Page<Order> findAllWithPagination(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> findOrdersById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public Optional<Order> createOrder(OrderDTO orderDTO) {
        return Optional.of(this.orderRepository.save(new Order(
                orderDTO.getReservation(),
                orderDTO.getTotal_price(),
                orderDTO.getOrderStatus(),
                orderDTO.getOrderDate())));
    }

    @Override
    public Optional<Order> editOrder(Long id, OrderDTO orderDTO) {
        Order order=this.orderRepository.findById(id).get();
        order.setReservation(orderDTO.getReservation());
        order.setTotal_price(order.getTotal_price());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setOrderDate(orderDTO.getOrderDate());
        return Optional.empty();
    }

    @Override
    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
}
