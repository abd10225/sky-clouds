package com.abdullah.quizapp.service;

import com.abdullah.quizapp.dao.OrderDao;
import com.abdullah.quizapp.dto.CreateOrderRequest;
import com.abdullah.quizapp.dto.OrderItemRequest;
import com.abdullah.quizapp.dao.ProductDao;
import com.abdullah.quizapp.exception.ResourceNotFoundException;
import com.abdullah.quizapp.model.Order;
import com.abdullah.quizapp.model.OrderItem;
import com.abdullah.quizapp.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    public Order createOrder(Integer customerId, List<OrderItemRequest> items) {
        Order order = new Order();
        order.setCustomerId(customerId);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();
        ;

        for (OrderItemRequest req : items) {
            Products product = productDao.findById(req.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            if (product.getStock() < req.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            // Deduct stock
            product.setStock(product.getStock() - req.getQuantity());
            productDao.save(product);

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(req.getQuantity());
            item.setPrice(product.getAmount());
            item.setOrder(order);
            orderItems.add(item);

            // Correct BigDecimal calculation
            BigDecimal quantity = BigDecimal.valueOf(req.getQuantity());
            totalAmount = totalAmount.add(product.getAmount().multiply(quantity));
        }

        order.setTotalAmount(totalAmount);
        order.setItems(orderItems);

        return orderDao.save(order);
    }


    public Order getOrderById(Integer orderId) {
        return orderDao.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
}


















