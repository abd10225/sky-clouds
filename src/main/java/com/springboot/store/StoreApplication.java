package com.springboot.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
//        SpringApplication.run(StoreApplication.class, args);
          ApplicationContext context =  SpringApplication.run(StoreApplication.class, args);
          var orderService = context.getBean(OrderService.class);

//        var orderService = new OrderService();
        orderService.setPaymentService(new PayPalService());
        orderService.placeOrder();
    }
    

}
