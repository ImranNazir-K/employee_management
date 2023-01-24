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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.OrderConstant;
import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.OrderHelper;
import com.ideas2it.fooddeliveryapp.model.OrderDetail;
import com.ideas2it.fooddeliveryapp.model.OrderItem;
import com.ideas2it.fooddeliveryapp.repository.OrderDetailRepository;
import com.ideas2it.fooddeliveryapp.service.OrderDetailService;
import com.ideas2it.fooddeliveryapp.util.OrderStatus;

/**
 * Service class for OrderDetail that implements OrderDetailService
 * interface to perform CRUD operations for OrderDetail.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger logger = LoggerFactory
            .getLogger(OrderDetailServiceImpl.class);

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
        OrderDetail orderDetail = calculateFoodAmount(OrderHelper
                .toOrderDetail(orderDetailDto));
        OrderDetail order = orderDetailRepository
                .save(orderDetail);
        logger.info("Order Created");
        return OrderHelper.toOrderDetailDto(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailDTO updateOrder(int orderId) {
        OrderDetail orderDetail = orderDetailRepository
                .findById(orderId).orElseThrow(() -> {
                    logger.warn(OrderConstant.ORDER_DETAIL_NOT_FOUND);
                    throw new NotFoundException(OrderConstant
                            .ORDER_DETAIL_NOT_FOUND);
                });
        orderDetail.setOrderStatus(OrderStatus.ACCEPTED);
        OrderDetail order = orderDetailRepository.save(orderDetail);

        logger.info("Order updated");
        return OrderHelper.toOrderDetailDto(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getOrders() {
        return OrderHelper.toOrderDetailDtos(orderDetailRepository
                .findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getAllOrdersByRestaurantId(int
            restaurantId) {
        List<OrderDetail> orderDetail = orderDetailRepository
                .findAllByRestaurantId(restaurantId);
        return OrderHelper.toOrderDetailDtos(orderDetail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getActiveOrdersByRestaurantId(int
            restaurantId) {
        List<OrderDetail> orderDetail = orderDetailRepository
                .getActiveOrdersByRestaurantId(restaurantId);
        return OrderHelper.toOrderDetailDtos(orderDetail);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getActiveOrdersToDeliver() {
        List<OrderDetail> orders = orderDetailRepository
                .getActiveOrdersToDeliver();
        return OrderHelper.toOrderDetailDtos(orders);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getOrdersByUserId(int userId) {
        List<OrderDetail> orders = orderDetailRepository
                .findAllByUserId(userId);
        return OrderHelper.toOrderDetailDtos(orders);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailDTO getOrderByUserId(int orderId, int userId) {
        OrderDetail orderDetail = orderDetailRepository
                .findOrderByUserId(orderId, userId);
        return OrderHelper.toOrderDetailDto(orderDetail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetail calculateFoodAmount(OrderDetail orderDetail) {
        List<OrderItem> orderItems = orderDetail.getOrderItems();

        orderItems.forEach(orderItem -> {
            float foodPrice = orderItem.getFood().getPrice();
            float amount = orderItem.getQuantity() * foodPrice;
            orderItem.setAmount(amount);
        });
        return getSubTotal(orderDetail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetail getSubTotal(OrderDetail orderDetail) {
        float totalAmount = 0;

        for (OrderItem orderItem : orderDetail.getOrderItems()) {
            totalAmount += orderItem.getAmount();
        }
        orderDetail.setSubTotal(totalAmount);
        return calculateTotalAmount(orderDetail);
    }

    /**
     * {@inheritDoc}
     */
    public OrderDetail calculateTotalAmount(OrderDetail orderDetail) {
        float subTotal = orderDetail.getSubTotal();

        byte discount = calculateDiscount(subTotal);
        float grandTotal = calculateGrandTotal(subTotal, discount);

        orderDetail.setDiscount(String.valueOf(discount) + "%");
        orderDetail.setGrandTotal(grandTotal);
        orderDetail.setSubTotal(subTotal);

        return orderDetail;
    }

    /**
     * {@inheritDoc}
     */
    public byte calculateDiscount(float subTotal) {
        byte discount = 5;

        if (1000 <= subTotal) {
            discount = 10;
        } else if (2000 <= subTotal) {
            discount = 20;
        } else if (5000 <= subTotal) {
            discount = 25;
        }
        return discount;
    }

    /**
     * {@inheritDoc}
     */
    public float calculateGrandTotal(float subTotal, byte discount) {
        if (0 < discount) {
            subTotal = subTotal - ((subTotal / 100) * discount);
        }
        return subTotal;
    }
}