/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.fooddeliveryapp.model.OrderDetail;

/**
 * Repository for Order that extends JpaRepository.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
