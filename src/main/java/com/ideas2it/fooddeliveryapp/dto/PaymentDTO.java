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
import java.time.LocalDateTime;

import com.ideas2it.fooddeliveryapp.constant.OrderPaymentConstant;
import com.ideas2it.fooddeliveryapp.util.PaymentMode;
import com.ideas2it.fooddeliveryapp.util.PaymentStatus;

/**
 * Data Transfer Object for the class Payment that consists of
 * variables like id, paymentMode, paymentType, totalAmount,
 * date and orderDto with setters and getters.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
public class PaymentDTO {

    private int id;

    private float subTotal;

    private String discount;

    private float grandTotal;

    @Enumerated(EnumType.STRING)
    @NotNull(message = OrderPaymentConstant.NOT_NULL_AMOUNT)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    @NotNull(message = OrderPaymentConstant.NOT_NULL_PAYMENT)
    private PaymentStatus paymentStatus;

    private LocalDateTime createdDate;

    private OrderDetailDTO order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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

    public OrderDetailDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDetailDTO order) {
        this.order = order;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}