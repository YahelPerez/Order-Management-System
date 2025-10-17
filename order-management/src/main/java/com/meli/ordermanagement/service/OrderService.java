package com.meli.ordermanagement.service;

import com.meli.ordermanagement.model.Order;
import com.meli.ordermanagement.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Orders.
 * This class acts as an intermediary between the Controller and the Repository.
 */
@Service
public class OrderService {

    // Spring injects an instance of OrderRepository here.
    @Autowired
    private OrderRepository OrderRepository;

    /**
     * Retrieves all orders from the database.
     * @return a list of all orders.
     */
    public List<Order> getAllOrders() {
        return OrderRepository.findAll();
    }

    /**
     * Retrieves a single order by its ID.
     * @param id The ID of the order to find.
     * @return an Optional containing the order if found, or an empty Optional if not.
     */
    public Optional<Order> getOrderById(Long id) {
        return OrderRepository.findById(id);
    }

    /**
     * Creates and saves a new order.
     * Sets the order date to the current time before saving.
     * @param order The order object to be saved.
     * @return the saved order with its generated ID.
     */
    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        return OrderRepository.save(order);
    }

    /**
     * Updates an existing order.
     * @param id The ID of the order to update.
     * @param orderDetails The new details for the order.
     * @return an Optional containing the updated order if it existed, or an empty Optional.
     */
    public Optional<Order> updateOrder(Long id, Order orderDetails) {
        return OrderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setCustomerName(orderDetails.getCustomerName());
                    existingOrder.setProductName(orderDetails.getProductName());
                    existingOrder.setQuantity(orderDetails.getQuantity());
                    existingOrder.setTotalPrice(orderDetails.getTotalPrice());
                    // The order date is generally not updated.
                    return OrderRepository.save(existingOrder);
                });
    }

    /**
     * Deletes an order by its ID.
     * @param id The ID of the order to delete.
     * @return true if the order existed and was deleted, false otherwise.
     */
    public boolean deleteOrder(Long id) {
        if (OrderRepository.existsById(id)) {
            OrderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}