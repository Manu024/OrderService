package com.example.orderservice.services;

import com.example.orderservice.models.Order;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private KafkaProducerService kafkaProducerService;

    public OrderService(OrderRepository orderRepository, KafkaProducerService kafkaProducerService) {
        this.orderRepository = orderRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public String createOrder(Order order) {
        Order savedorder = orderRepository.save(order);
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getTokenAttributes().get("sub");

        sendOrderCreationEmail("ecommapp@gmail.com", userEmail, "Order placed successfully");
        return "Order placed successfully";
    }

    public void sendOrderCreationEmail(String from, String to, String message) {
        kafkaProducerService.sendOrderCreationEmail(from, to, message);
    }
}
