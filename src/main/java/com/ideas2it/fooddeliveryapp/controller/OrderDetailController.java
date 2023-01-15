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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.service.OrderDetailService;

/**
 * Class as controller for the Food Delivery Application that does
 * CRUD operations for orders.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/orderdetails")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    /**
     * creates an order that clients made as request.
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
     * @return List<OrderDTO> list of orders.
     */
    @GetMapping
    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetailService.getOrderDetails();
    }

    /**
     * Gets that particular order the client requested.
     *
     * @param orderId of an order as int.
     * @return orderDto as OrderDTO instance that consists an order.
     */
    @GetMapping("/{orderId}")
    public OrderDetailDTO getOrderDetailById(@PathVariable int orderId) {
        return orderDetailService.getOrderDetailById(orderId);
    }

    /**
     * updates the particular order the client requested.
     *
     * @param orderDetailDto as OrderDTO instance consists an order.
     * @return orderDto as OrderDTO instance which was updated.
     */
    @PutMapping
    public OrderDetailDTO updateOrder(@RequestBody @Valid OrderDetailDTO
            orderDetailDto) throws NotFoundException {
        return orderDetailService.updateOrder(orderDetailDto);
    }
}