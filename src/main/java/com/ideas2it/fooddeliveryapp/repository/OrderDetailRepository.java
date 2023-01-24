/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.fooddeliveryapp.model.OrderDetail;

/**
 * Repository for Order Detail that extends JpaRepository and
 * provides additional custom methods to perform CRUD operations.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,
        Integer> {

    /**
     * Gets all the pending orders of a restaurant by id if no Order
     * Details are found for that restaurant returns empty List.
     *
     * @param restaurantId id of a user.
     * @return list of Order Detail objects of a particular user.
     */
    List<OrderDetail> findAllByRestaurantId(int restaurantId);

    /**
     * Gets all the pending orders of a restaurant by id if no Order
     * Details are found for that restaurant returns empty List.
     *
     * @param restaurantId the id of a restaurant as int.
     * @return list of OrderDetails as List<OrderDetail>.
     */
    @Query("SELECT od FROM OrderDetail od WHERE od.orderStatus = 'PENDING'"
            + " AND od.restaurant.id = :restaurantId")
    List<OrderDetail> getActiveOrdersByRestaurantId(int restaurantId);


    /**
     * Gets all the orders of a user by user id if no Order
     * Details are found for that user returns empty List.
     *
     * @param userId id of a user.
     * @return list of Order Detail objects of a particular user.
     */
    List<OrderDetail> findAllByUserId(int userId);

    /**
     * Gets the orders which are accepted by restaurants to Deliver
     * the order.
     *
     * @return list of OrderDetails as List<OrderDetail>.
     */
    @Query("SELECT od FROM OrderDetail od WHERE od.orderStatus = 'ACCEPTED'")
    List<OrderDetail> getActiveOrdersToDeliver();

    /**
     * Gets the particular order of a user by orderId.
     *
     * @param orderId the id of a orderDetail as int.
     * @param userId  the id of a user as int.
     * @return OrderDetail object that contains an order.
     */
    @Query("SELECT od FROM OrderDetail od WHERE od.id = :orderId AND"
            + " od.user.id = :userId ")
    OrderDetail findOrderByUserId(int orderId, int userId);
}