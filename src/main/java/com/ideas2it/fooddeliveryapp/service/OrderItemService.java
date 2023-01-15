/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service;

import java.util.List;

import com.ideas2it.fooddeliveryapp.dto.OrderItemDTO;
import com.ideas2it.fooddeliveryapp.model.OrderItem;

/**
 * @author IMRAN NAZIR K
 * @version 1.0
 */
public interface OrderItemService {

    /**
     * @param orderItemDto
     * @return
     */
    OrderItemDTO createOrderItems(OrderItemDTO orderItemDto);

    /**
     * @return
     */
    List<OrderItemDTO> getOrderItems();

    /**
     * @return
     */
    OrderItemDTO getOrderItemById(int orderItemId);

    /**
     * @param orderItemDto
     * @return
     */
    OrderItemDTO updateOrderItems(OrderItemDTO orderItemDto);

    /**
     * @param orderItem
     * @return
     */
    float calculateAmount(OrderItem orderItem);
}