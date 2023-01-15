/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.OrderItemDTO;
import com.ideas2it.fooddeliveryapp.service.OrderItemService;

/**
 * @author IMRAN NAZIR K
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/orderitems")
public class OrderItemController {

    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * @param orderItemDto
     * @return
     */
    @PostMapping
    public OrderItemDTO createOrderItem(@RequestBody OrderItemDTO orderItemDto) {
        return orderItemService.createOrderItems(orderItemDto);
    }

    /**
     * @return
     */
    @GetMapping
    public List<OrderItemDTO> getOrderItems() {
        return orderItemService.getOrderItems();
    }

    /**
     * @return
     */
    @GetMapping("/{orderItemId}")
    public OrderItemDTO getOrderItemById(@PathVariable int orderItemId) {
        return orderItemService.getOrderItemById(orderItemId);
    }

    /**
     * @param orderItemDto
     * @return
     */
    @PutMapping
    public OrderItemDTO updateOrderItem(@RequestBody OrderItemDTO orderItemDto) {
        return orderItemService.updateOrderItems(orderItemDto);
    }
}
