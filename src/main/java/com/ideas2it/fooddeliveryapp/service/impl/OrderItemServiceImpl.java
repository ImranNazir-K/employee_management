/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.dto.OrderItemDTO;
import com.ideas2it.fooddeliveryapp.helper.OrderHelper;
import com.ideas2it.fooddeliveryapp.model.OrderItem;
import com.ideas2it.fooddeliveryapp.repository.OrderItemRepository;
import com.ideas2it.fooddeliveryapp.service.OrderItemService;

/**
 * Service class for OrderItem that implements OrderItemService
 * interface to perform CRUD operations for OrderItem.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private static final Logger logger = LoggerFactory
            .getLogger(OrderItemServiceImpl.class);

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderItemDTO createOrderItems(OrderItemDTO orderItemDto) {
        OrderItem orderItem = orderItemRepository
                .save(OrderHelper.toOrderItem(orderItemDto));
        logger.info("Order Items Created");
        return OrderHelper.toOrderItemDto(orderItem);
    }
}