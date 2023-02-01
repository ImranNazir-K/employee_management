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

import com.ideas2it.fooddeliveryapp.model.Delivery;

/**
 * Repository for Delivery that extends JpaRepository and provides
 * additional custom method to perform functionality operation.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    /**
     * Gets all the deliveries as Delivery objects by a given
     * user id.
     *
     * @param userId The id of the user.
     * @return List of deliveries as Delivery objects.
     */
    List<Delivery> findAllByUserId(int userId);
}
