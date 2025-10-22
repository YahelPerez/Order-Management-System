package com.meli.ordermanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.ordermanagement.model.Order;
import com.meli.ordermanagement.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest the full context of the application for testing.
@SpringBootTest
// @AutoConfigureMockMvc provides us with a tool to simulate HTTP requests.
@AutoConfigureMockMvc
// We tell Spring to activate the "test" profile for these tests.
@ActiveProfiles("test")
class OrderControllerTest {

    // MockMvc allows us to send HTTP requests to our controller without lifting a real server.
    @Autowired
    private MockMvc mockMvc;

    // We use @MockBean to replace the real service with a "fake" one in the context of the app.
    // This allows us to monitor its behavior and test the controller in isolation.
    @MockBean
    private OrderService orderService;

    // ObjectMapper helps us convert Java objects to JSON and vice versa.
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenGetAllOrders_shouldReturnOkAndOrderList() throws Exception {
        // Arrange
        Order order1 = new Order();
        order1.setId(1L);
        order1.setCustomerName("Arturo");
        Order order2 = new Order();
        order2.setId(2L);
        order2.setCustomerName("Juan");
        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders"))
                .andExpect(status().isOk()) // We verify that the status of the response is 200 OK.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2)) // We verify that the list has 2 items.
                .andExpect(jsonPath("$[0].customerName").value("Arturo")); // We verify the name of the first client.
    }

    @Test
    void whenCreateOrder_shouldReturnCreatedAndOrder() throws Exception {
        // Arrange
        Order newOrder = new Order();
        newOrder.setCustomerName("Test Customer");

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setCustomerName("Test Customer");
        savedOrder.setOrderDate(LocalDateTime.now());

        when(orderService.createOrder(any(Order.class))).thenReturn(savedOrder);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newOrder))) // We convert our object to JSON.
                .andExpect(status().isCreated()) // We verify that the status is 201 Created.
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerName").value("Test Customer"));
    }

    @Test
    void whenGetOrderById_withInvalidId_shouldReturnNotFound() throws Exception {
        // Arrange
        when(orderService.getOrderById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/99"))
                .andExpect(status().isNotFound()); // We verify that the status is 404 Not Found.
    }
}