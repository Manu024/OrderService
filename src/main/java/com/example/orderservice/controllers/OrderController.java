package com.example.orderservice.controllers;

import com.example.orderservice.dtos.OrderRequestDto;
import com.example.orderservice.models.Order;
import com.example.orderservice.models.OrderItem;
import com.example.orderservice.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        // Create order
        Order order = new Order();
        order.setUserId(orderRequestDto.getUserId());
        order.setAddress(orderRequestDto.getAddress());
        int totalPrice = orderRequestDto.getOrderItems().stream().mapToInt(orderItem -> (int) (orderItem.getPrice() * orderItem.getQuantity())).sum();
        List<OrderItem> orderItemList = orderRequestDto.getOrderItems();
        order.setOrderItems(orderItemList);
        order.setTotalPrice(totalPrice);
        order.setStatus("PENDING");
        return orderService.createOrder(order);
    }
}
