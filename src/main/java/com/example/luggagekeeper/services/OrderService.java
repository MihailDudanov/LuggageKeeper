package com.example.luggagekeeper.services;

import com.example.luggagekeeper.models.Order;
import com.example.luggagekeeper.models.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> listAllOrders();
    Page<Order> findAllWithPagination(Pageable pageable);
    Optional<Order> findOrdersById(Long id);
    Optional<Order> createOrder(OrderDTO orderDTO);
    Optional<Order> editOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
}
