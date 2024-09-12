package com.example.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "order_items")
public class OrderItem extends BaseModel {
    private Long productId;
    @ManyToOne
    @JoinColumn(name = "order_id") // This column will hold the foreign key to Order
    private Order order;
    private String productName;
    private int quantity;
    private double price;

    public OrderItem(Long productId, Order order, String productName, int quantity, double price) {
        this.productId = productId;
        this.order = order;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {

    }
}
