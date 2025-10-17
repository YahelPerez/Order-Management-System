package com.meli.ordermanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Represents an Order in the online store.
 * This is a JPA entity that will be mapped to a database table named 'orders'.
 */
@Entity
@Table(name = "orders")
@Data // Lombok annotation to automatically generate getters, setters, toString, etc.
public class Order {

    /**
     * The unique identifier for the order.
     * It is the primary key and is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the customer who placed the order.
     */
    private String customerName;

    /**
     * The date and time when the order was placed.
     */
    private LocalDateTime orderDate;

    /**
     * The name of the product being ordered.
     */
    private String productName;

    /**
     * The quantity of the product being ordered.
     */
    private int quantity;

    /**
     * The total price of the order.
     */
    private double totalPrice;
}