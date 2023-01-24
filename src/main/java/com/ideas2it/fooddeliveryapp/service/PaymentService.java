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

import com.ideas2it.fooddeliveryapp.dto.PaymentDTO;

/**
 * Interface for PaymentServiceImpl class to perform CRUD
 * operations for Food delivery application.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
public interface PaymentService {

    /**
     * Gets all the payments. if no Payments are found returns empty
     * List.
     *
     * @return List<PaymentDTO> consists all the payments.
     */
    List<PaymentDTO> getPayments();
}