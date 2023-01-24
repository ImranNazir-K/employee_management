/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.OrderItemDTO;
import com.ideas2it.fooddeliveryapp.service.OrderItemService;

/**
 * Controller class for Handling  the incoming requests to validate
 * the user input for performing crud operations for OrderItem.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
@RestController
@RequestMapping("/api/v1/orderitems")
public class OrderItemController {

    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * Creates an orderItem that clients made as request.
     *
     * @param orderItemDto as OrderItemDTO instance consists an
     *                     orderItem.
     * @return orderItemDto which was created.
     */
    @PostMapping
    public OrderItemDTO createOrderItem(@RequestBody OrderItemDTO
            orderItemDto) {
        return orderItemService.createOrderItems(orderItemDto);
    }
}