/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.service.OrderDetailService;

/**
 * Controller class for Handling the incoming requests to validate
 * the user input for performing crud operations for OrderDetail.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
@RestController
@RequestMapping("/api/v1/orderdetails")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    /**
     * Creates a OrderDetail by calculating subTotal, grandTotal and
     * discount for the order and sets those attributes in the
     * OrderDetail object before creating a OrderDetails.
     *
     * @param orderDetailDto as OrderDetailDTO object that consists
     *                       an orderDetail.
     * @return order as OrderDetailDTO object that was created.
     */
    @PostMapping
    public OrderDetailDTO createOrder(@RequestBody @Valid OrderDetailDTO
            orderDetailDto) {
        return orderDetailService.createOrder(orderDetailDto);
    }

    /**
     * Gets all the orders if no Order Details are found returns
     * empty List.
     *
     * @return List<OrderDetailDTO> consists all the orderDetails.
     */
    @GetMapping
    public List<OrderDetailDTO> getOrders() {
        List<OrderDetailDTO> orders = orderDetailService.getOrders();
        return orderDetailService.getOrders();
    }

    /**
     * Gets all the orders of a user by id if no Order
     * Details are found for that user returns empty List.
     *
     * @param userId the id of a user as int.
     * @return List<OrderDetailDto> contains all the orderDetails.
     */
    @GetMapping("/users/{userId}")
    public List<OrderDetailDTO> getOrdersByUserId(@PathVariable int
            userId) {
        return orderDetailService.getOrdersByUserId(userId);
    }

    /**
     * Gets the particular order of a user by orderId.
     *
     * @param orderId the id of a orderDetail as int.
     * @param userId  the id of a user as int.
     * @return OrderDetail object that contains an order.
     */
    @GetMapping("/{orderId}/users/{userId}")
    public OrderDetailDTO getOrderByUserId(@PathVariable int orderId,
            @PathVariable int userId) {
        return orderDetailService.getOrderByUserId(orderId, userId);
    }
}