package com.example.luggagekeeper.controller;

import com.example.luggagekeeper.models.Order;
import com.example.luggagekeeper.models.dto.OrderDTO;
import com.example.luggagekeeper.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://localhost:3000")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }
    @GetMapping
    public ResponseEntity<List<Order>> showAllOrders(){
        List<Order>orders = this.orderService.listAllOrders();
        if(!orders.isEmpty()){
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Order> save(@RequestBody OrderDTO orderDTO){
        return this.orderService.createOrder(orderDTO)
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Order> save(@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        return this.orderService.editOrder(id,orderDTO)
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.orderService.deleteOrder(id);
        if(this.orderService.findOrdersById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
