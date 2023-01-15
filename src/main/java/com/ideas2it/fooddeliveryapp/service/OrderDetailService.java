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

import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;

/**
 * Interface for OrderServiceImpl class to perform CRUD operations
 * for Food delivery application.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
@Service
public interface OrderDetailService {

    /**
     * creates order.
     *
     * @param orderDetailDTO as OrderDTO object that consists an
     *                       order.
     * @return orderDto as OrderDTO object that was created.
     */
    OrderDetailDTO createOrder(OrderDetailDTO orderDetailDTO);

    /**
     * Gets all the orders from the Database.
     *
     * @return List<OrderDTO> consists all the orders.
     */
    List<OrderDetailDTO> getOrderDetails();

    /**
     * Gets the particular order from the database based on the id
     * given. checks whether that particular order is
     * available if not found throws NotFoundException.
     *
     * @param orderId of an order.
     * @return OrderDTO object that contains an order.
     */
    OrderDetailDTO getOrderDetailById(int orderId);

    /**
     * Updates the particular order from the database based on the id
     * given. checks whether that particular order is
     * available if not found throws NotFoundException.
     *
     * @param orderDetailDto orderDTO as OrderDTO object that consists
     *                       order.
     * @return orderDto as OrderDTO object that was updated.
     */
    OrderDetailDTO updateOrder(OrderDetailDTO orderDetailDto);

    /**
     * Calculates the total amount for the order made by client.
     *
     * @return the calculated total amount.
     */
//    float calculateSubTotal();
}