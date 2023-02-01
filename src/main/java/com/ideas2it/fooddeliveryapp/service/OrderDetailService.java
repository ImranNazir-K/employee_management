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
import com.ideas2it.fooddeliveryapp.model.OrderDetail;

/**
 * Interface for OrderDetailServiceImpl class to perform CRUD
 * operations for OrderDetail.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public interface OrderDetailService {

    /**
     * Creates a OrderDetail by calculating subTotal, grandTotal and
     * discount for the order and sets those attributes in the
     * OrderDetail object before creating a OrderDetails.
     *
     * @param orderDetailDto as OrderDetailDTO object that consists
     *                       an orderDetail.
     * @return order as OrderDetailDTO object that was created.
     */
    OrderDetailDTO createOrder(OrderDetailDTO orderDetailDto);

    /**
     * Updates an orderDetail by changing the orderStatus to
     * accepted.
     *
     * @param orderDetailId the id of an order.
     * @return order as OrderDetailDTO object that was updated.
     */
    OrderDetailDTO updateOrder(int orderDetailId);

    /**
     * Gets all the orders if no Order Details are found returns
     * empty List.
     *
     * @return List<OrderDetailDTO> consists all the orderDetails.
     */
    List<OrderDetailDTO> getOrders();

    /**
     * Gets all the orders of a user by id if no Order
     * Details are found for that user returns empty List.
     *
     * @param userId the id of a user as int.
     * @return List<OrderDetailDto> contains all the orderDetails.
     */
    List<OrderDetailDTO> getOrdersByUserId(int userId);

    /**
     * Gets the particular order of a user by orderId.
     *
     * @param orderId the id of a orderDetail as int.
     * @param userId  the id of a user as int.
     * @return OrderDetail object that contains an order.
     */
    OrderDetailDTO getOrderByUserId(int orderId, int userId);

    /**
     * Gets all the orderDetails of a restaurant by id if no Order
     * Details are found for that restaurant returns empty List.
     *
     * @return List<OrderDetailDto> contains all the orderDetails.
     */
    List<OrderDetailDTO> getAllOrdersByRestaurantId(int restaurantId);

    /**
     * Gets all the orderDetails of a restaurant by id if no Order
     * Details are found for that restaurant returns empty List.
     *
     * @return List<OrderDetailDto> contains all the orderDetails.
     */
    List<OrderDetailDTO> getActiveOrdersByRestaurantId(int restaurantId);

    /**
     * Gets the orders which are accepted by restaurants to Deliver
     * the order.
     *
     * @return list of Orders with Payment as List<PaymentDTO>.
     */
    List<OrderDetailDTO> getActiveOrdersToDeliver();

    /**
     * Calculates the total amount for a particular food by
     * multiplying its price and quantity.
     *
     * @param orderDetail as OrderDetail object.
     * @return orderDetail object with total amount calculated.
     */
    OrderDetail calculateFoodAmount(OrderDetail orderDetail);

    /**
     * Calculates the subTotal for the order client made.
     *
     * @param orderDetail as OrderDetail Object that contains an
     *                    order.
     * @return orderDetail as OrderDetail Object with calculated
     * subTotal.
     */
    OrderDetail getSubTotal(OrderDetail orderDetail);

    /**
     * Calculates the total amount, discount and grand total for the
     * order ordered by a user and sets those values in the
     * PaymentDTO object.
     *
     * @param orderDetail as PaymentDTO object.
     * @return paymentDto as PaymentDTO object that to be saved.
     */
    OrderDetail calculateTotalAmount(OrderDetail orderDetail);

    /**
     * Calculates the discount amount for the order using the
     * subtotal amount.
     *
     * @param subTotal total amount of the order made by client.
     * @return discount calculated as byte.
     */
    byte calculateDiscount(float subTotal);

    /**
     * Calculates the grand total amount for the order by subtracting
     * the discount amount from the subtotal amount.
     *
     * @param subTotal total amount of the order as float.
     * @param discount applied for that subtotal amount as byte.
     * @return calculated grand total amount as float.
     */
    float calculateGrandTotal(float subTotal, byte discount);
}