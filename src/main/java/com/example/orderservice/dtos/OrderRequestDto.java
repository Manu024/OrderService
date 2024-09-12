package com.example.orderservice.dtos;

import com.example.orderservice.models.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Long userId;
    private List<OrderItem> orderItems;
    private double totalPrice;
    private String address;

    public OrderRequestDto(Long userId, List<OrderItem> orderItems, double totalPrice, String address) {
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.address = address;
    }

    public OrderRequestDto() {
    }
}
