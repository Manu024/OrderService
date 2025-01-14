package com.example.orderservice.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        Date date = new Date();
        createdAt = date;
        updatedAt = date;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
