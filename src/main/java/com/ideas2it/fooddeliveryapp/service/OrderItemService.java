/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service;

import com.ideas2it.fooddeliveryapp.dto.OrderItemDTO;

/**
 * Interface for OrderItemServiceImpl class to perform CRUD
 * operations for OrderItem.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
public interface OrderItemService {

    /**
     * Creates an orderItem.
     *
     * @param orderItemDto as OrderItemDTO object that consists
     *                     an orderItem.
     * @return orderDto as OrderItemDTO object that was created.
     */
    OrderItemDTO createOrderItems(OrderItemDTO orderItemDto);
}