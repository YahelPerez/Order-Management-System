package com.meli.ordermanagement.service;

import com.meli.ordermanagement.model.Order;
import com.meli.ordermanagement.Repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    // Create a "fake" version of the repository. Will not use the actual database.
    @Mock
    private OrderRepository orderRepository;

    // Creates a real instance of the service, but injects the "fake" repository into it.
    @InjectMocks
    private OrderService orderService;

    private Order order1;
    private Order order2;

    @BeforeEach
    void setUp() {
        // We prepare test data that we will use in various tests.
        order1 = new Order();
        order1.setId(1L);
        order1.setCustomerName("Arturo Bandini");
        order1.setOrderDate(LocalDateTime.now());
        order1.setProductName("Laptop");
        order1.setQuantity(1);
        order1.setTotalPrice(1500.0);

        order2 = new Order();
        order2.setId(2L);
        order2.setCustomerName("Juan Ventura");
    }

    @Test
    void whenGetAllOrders_shouldReturnOrderList() {
        // Arrange (Preparar)
        // We tell our fake repository what to return when your findAll() method is called.
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        // Act (Actuar)
        // We call the method we want to test.
        List<Order> orders = orderService.getAllOrders();

        // Assert (Verificar)
        // We check that the result is as expected.
        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll(); // Verificamos que el método findAll() se llamó exactamente una vez.
    }

    @Test
    void whenGetOrderById_withValidId_shouldReturnOrder() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));

        // Act
        Optional<Order> foundOrder = orderService.getOrderById(1L);

        // Assert
        assertTrue(foundOrder.isPresent());
        assertEquals("Arturo Bandini", foundOrder.get().getCustomerName());
    }

    @Test
    void whenGetOrderById_withInvalidId_shouldReturnEmpty() {
        // Arrange
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Order> foundOrder = orderService.getOrderById(99L);

        // Assert
        assertFalse(foundOrder.isPresent());
    }

    @Test
    void whenCreateOrder_shouldReturnSavedOrder() {
        // Arrange
        when(orderRepository.save(any(Order.class))).thenReturn(order1);

        // Act
        Order newOrder = new Order(); // Un objeto de orden sin ID
        Order savedOrder = orderService.createOrder(newOrder);

        // Assert
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getOrderDate()); // Verificamos que el servicio asignó la fecha.
        assertEquals(1L, savedOrder.getId());
    }

    @Test
    void whenDeleteOrder_withValidId_shouldReturnTrue() {
        // Arrange
        when(orderRepository.existsById(1L)).thenReturn(true);
        // doNothing() se usa para métodos que no devuelven nada (void).
        doNothing().when(orderRepository).deleteById(1L);

        // Act
        boolean result = orderService.deleteOrder(1L);

        // Assert
        assertTrue(result);
        verify(orderRepository, times(1)).deleteById(1L); // Verificamos que se intentó borrar.
    }

    @Test
    void whenDeleteOrder_withInvalidId_shouldReturnFalse() {
        // Arrange
        when(orderRepository.existsById(99L)).thenReturn(false);

        // Act
        boolean result = orderService.deleteOrder(99L);

        // Assert
        assertFalse(result);
        verify(orderRepository, never()).deleteById(99L); // Verificamos que NUNCA se intentó borrar.
    }
}