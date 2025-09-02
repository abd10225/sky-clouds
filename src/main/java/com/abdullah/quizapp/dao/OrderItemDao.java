package com.abdullah.quizapp.dao;

import com.abdullah.quizapp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

}
