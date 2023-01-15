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
import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.OrderPaymentHelper;
import com.ideas2it.fooddeliveryapp.model.OrderDetail;
import com.ideas2it.fooddeliveryapp.repository.OrderDetailRepository;
import com.ideas2it.fooddeliveryapp.service.OrderDetailService;

/**
 * Service class for Order that implements OrderService interface to
 * perform CRUD operations for Food Delivery Application.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository
            orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailDTO createOrder(OrderDetailDTO orderDetailDto) {
        System.out.println(orderDetailDto);
        System.out.println("-----------------------------");
        OrderDetail orderDetail = OrderPaymentHelper.toOrderDetail(orderDetailDto);
        System.out.println(orderDetail);
        return OrderPaymentHelper.toOrderDetailDto(orderDetailRepository.save(orderDetail));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getOrderDetails() {
        return OrderPaymentHelper.toOrderDetailDtos(orderDetailRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailDTO getOrderDetailById(int orderId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(OrderPaymentConstant
                        .ORDER_NOT_FOUND));
        return OrderPaymentHelper.toOrderDetailDto(orderDetail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailDTO updateOrder(OrderDetailDTO orderDetailDto) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailDto
                .getId()).orElseThrow(() -> new NotFoundException
                (OrderPaymentConstant.ORDER_NOT_FOUND));
        OrderDetail order = OrderPaymentHelper.toOrderDetail(orderDetailDto);

        return OrderPaymentHelper.toOrderDetailDto(orderDetailRepository
                .save(order));
    }
}