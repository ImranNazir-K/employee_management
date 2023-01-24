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
 * Controller class for Handling  the incoming requests to validate
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
     * Creates an order detail.
     *
     * @param orderDetailDto as OrderDTO instance consists an order.
     * @return orderDto which was created.
     */
    @PostMapping
    public OrderDetailDTO createOrder(@RequestBody @Valid OrderDetailDTO
            orderDetailDto) {
        return orderDetailService.createOrder(orderDetailDto);
    }

    /**
     * Gets all the orders that are available.
     *
     * @return list of orders as List<OrderDTO>.
     */
    @GetMapping
    public List<OrderDetailDTO> getOrders() {
        return orderDetailService.getOrders();
    }

    /**
     * Gets all the orderDetails of a user by id if no Order
     * Details are found for that user returns empty List.
     *
     * @param userId of a user as int.
     * @return List<OrderDetailDto> contains all the orderDetails.
     */
    @GetMapping("/users/{userId}")
    public List<OrderDetailDTO> getOrdersByUserId(@PathVariable int
            userId) {
        return orderDetailService.getOrdersByUserId(userId);
    }

    /**
     * Gets all the orders with payments of a user by id if no Order
     * Details are found for that user returns empty List.
     *
     * @param userId the id of a user as int.
     * @return List<OrderDetailDto> contains all the orderDetails.
     */
    @GetMapping("/{orderId}/users/{userId}")
    public OrderDetailDTO getOrderById(@PathVariable int orderId,
            @PathVariable int userId) {
        return orderDetailService.getOrderByUserId(orderId, userId);
    }
}