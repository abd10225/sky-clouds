package com.abdullah.quizapp.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateOrderRequest {
    private Integer customerId;
    private List<OrderItemRequest> items;
}