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
 * Interface for PaymentServiceImpl class to perform CRUD operations
 * for Food delivery application.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
public interface PaymentService {

    /**
     * creates payment.
     *
     * @param paymentDto as paymentDTO object that consists a payment.
     * @return paymentDto as PaymentDTO object that was created.
     */
    PaymentDTO createPayment(PaymentDTO paymentDto);

    /**
     * Gets all the payments from the Database.
     *
     * @return List<PaymentDTO> consists all the payments.
     */
    List<PaymentDTO> getPayments();

    /**
     * Gets the particular payment from the database based on the
     * given id. checks whether that particular payment is
     * available if not found throws NotFoundException.
     *
     * @param paymentId of a payment.
     * @return PaymentDTO object that contains a payment.
     */
    PaymentDTO getPaymentById(int paymentId);

    /**
     * Updates the particular payment from the database based on the
     * given id. checks whether that particular payment is
     * available if not found throws NotFoundException.
     *
     * @param paymentDto as paymentDTO object that consists a
     *                   payment.
     * @return paymentDto as PaymentDTO object that was updated.
     */
    PaymentDTO updatePayment(PaymentDTO paymentDto);
}