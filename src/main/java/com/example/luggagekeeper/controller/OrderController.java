package com.example.luggagekeeper.controller;

import com.example.luggagekeeper.models.Order;
import com.example.luggagekeeper.models.dto.OrderDTO;
import com.example.luggagekeeper.services.OrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> showAllOrders() {
        List<Order> orders = this.orderService.listAllOrders();
        return orders.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(orders);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> save(@RequestBody OrderDTO orderDTO) {
        return this.orderService.createOrder(orderDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Order> save(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return this.orderService.editOrder(id, orderDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.orderService.deleteOrder(id);
        return this.orderService.findOrdersById(id).isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    // ✅ New: Handle Stripe Payment Intent
    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody Map<String, Object> data) {
        try {
            Stripe.apiKey = stripeSecretKey; // Ensure this is set correctly
            int amount = (int) data.get("amount"); // Ensure amount is in cents

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) amount)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            Map<String, String> response = new HashMap<>();
            response.put("clientSecret", intent.getClientSecret());
            return ResponseEntity.ok(response);

        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment intent");
        }
    }
}
