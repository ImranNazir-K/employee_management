/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.OrderPaymentConstant;
import com.ideas2it.fooddeliveryapp.dto.OrderItemDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.OrderPaymentHelper;
import com.ideas2it.fooddeliveryapp.model.OrderItem;
import com.ideas2it.fooddeliveryapp.repository.OrderItemRepository;
import com.ideas2it.fooddeliveryapp.service.OrderItemService;

/**
 * @author IMRAN NAZIR K
 * @version 1.0
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderItemDTO createOrderItems(OrderItemDTO orderItemDto) {
        OrderItem orderItem = OrderPaymentHelper.toOrderItem(orderItemDto);
        orderItem.setAmount(calculateAmount(orderItem));
        return OrderPaymentHelper.toOrderItemDto(orderItemRepository
                .save(orderItem));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderItemDTO> getOrderItems() {
        return OrderPaymentHelper.toOrderItemDtos(orderItemRepository
                .findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderItemDTO getOrderItemById(int orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new NotFoundException(OrderPaymentConstant
                        .ORDER_ITEM_NOT_FOUND));
        return OrderPaymentHelper.toOrderItemDto(orderItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderItemDTO updateOrderItems(OrderItemDTO orderItemDto) {
        OrderItem orderItem = orderItemRepository.findById(orderItemDto.getId())
                .orElseThrow(() -> new NotFoundException(OrderPaymentConstant
                        .ORDER_ITEM_NOT_FOUND));
        OrderItem order = OrderPaymentHelper.toOrderItem(orderItemDto);
        return OrderPaymentHelper.toOrderItemDto(order);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public float calculateAmount(OrderItem orderItem) {
        return orderItem.getFood().getPrice() * orderItem.getQuantity();
    }
}