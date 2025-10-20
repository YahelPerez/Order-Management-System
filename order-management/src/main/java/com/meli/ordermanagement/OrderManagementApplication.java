package com.meli.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// We are explicitly telling Spring to scan all sub-packages within "com.meli.ordermanagement"
@SpringBootApplication(scanBasePackages = "com.meli.ordermanagement")
public class OrderManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApplication.class, args);
    }

}