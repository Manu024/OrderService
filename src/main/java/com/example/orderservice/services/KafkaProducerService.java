package com.example.orderservice.services;

import com.example.orderservice.dtos.OrderCreationEmailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private KafkaTemplate<String, Object> kafkaTemplate;
    private ObjectMapper objectMapper;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrderCreationEmail(String from, String to, String message) {
        System.out.println("producer called");
        OrderCreationEmailDto orderCreateEmailDto = new OrderCreationEmailDto();
        orderCreateEmailDto.setFrom(from);
        orderCreateEmailDto.setTo(to);
        orderCreateEmailDto.setMessage(message);
        try {
            kafkaTemplate.send("order-create", objectMapper.writeValueAsString(orderCreateEmailDto));
        } catch (Exception e) {
            System.out.println("Error publishing msg to kafka");
            throw new RuntimeException(e);
        }
    }
}
