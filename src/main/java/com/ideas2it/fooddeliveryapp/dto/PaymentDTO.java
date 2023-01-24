/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import com.ideas2it.fooddeliveryapp.constant.OrderConstant;
import com.ideas2it.fooddeliveryapp.util.PaymentMode;
import com.ideas2it.fooddeliveryapp.util.PaymentStatus;

/**
 * Data Transfer Object class for Payment that consists of
 * fields like id, paymentMode, paymentType, totalAmount, subTotal,
 * discount, createdDate and OrderDTO with setters and getters.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
public class PaymentDTO {

    private int id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = OrderConstant.NOT_NULL_PAYMENT_MODE)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    @NotNull(message = OrderConstant.NOT_NULL_PAYMENT_STATUS)
    private PaymentStatus paymentStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}