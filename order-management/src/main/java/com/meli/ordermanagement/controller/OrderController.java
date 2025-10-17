package com.meli.ordermanagement.controller;

import com.meli.ordermanagement.model.Order;
import com.meli.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing orders.
 * This class exposes a set of endpoints to perform CRUD operations on orders.
 */
@RestController
@RequestMapping("/api/orders") // Base URL for all endpoints in this controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Endpoint to get all orders.
     * HTTP GET /api/orders
     * @return a list of all orders.
     */
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Endpoint to get a single order by its ID.
     * HTTP GET /api/orders/{id}
     * @param id The ID of the order.
     * @return a ResponseEntity containing the order if found, or a 404 Not Found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok) // If order is present, return 200 OK with the order
                .orElse(ResponseEntity.notFound().build()); // Otherwise, return 404 Not Found
    }

    /**
     * Endpoint to create a new order.
     * HTTP POST /api/orders
     * @param order The order data from the request body.
     * @return a ResponseEntity with the created order and a 201 Created status.
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing order.
     * HTTP PUT /api/orders/{id}
     * @param id The ID of the order to update.
     * @param orderDetails The new order data from the request body.
     * @return a ResponseEntity with the updated order or a 404 Not Found status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderService.updateOrder(id, orderDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to delete an order.
     * HTTP DELETE /api/orders/{id}
     * @param id The ID of the order to delete.
     * @return a ResponseEntity with a 204 No Content status if successful, or 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.deleteOrder(id)) {
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the order didn't exist
        }
    }
}