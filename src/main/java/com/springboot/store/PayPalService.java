package com.springboot.store;

import org.springframework.stereotype.Service;

@Service
public class PayPalService implements PaymentService{
    @Override
    public void processPayment(double amount) {
        System.out.println("PAYPAL");
        System.out.println(amount);

    }
}
