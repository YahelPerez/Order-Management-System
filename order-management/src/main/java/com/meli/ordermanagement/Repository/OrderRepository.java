package com.meli.ordermanagement.Repository;

import com.meli.ordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Order entity.
 * This interface extends JpaRepository, which provides standard CRUD operations
 * (Create, Read, Update, Delete) for the Order entity out of the box.
 *
 * @Repository marks this interface as a Spring Data repository component.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Spring Data JPA will automatically implement methods for CRUD operations.
    // No need to write any code here!
}