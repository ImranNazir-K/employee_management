/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.ideas2it.fooddeliveryapp.constant.OrderPaymentConstant;

/**
 * @author IMRAN NAZIR K
 * @version 1.0
 */
public class OrderItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = OrderPaymentConstant.QUANTITY_REGEX,
            message = OrderPaymentConstant.INVALID_QUANTITY)
    @NotNull(message = OrderPaymentConstant.NOT_NULL_QUANTITY)
    private String quantity;

    @Pattern(regexp = OrderPaymentConstant.AMOUNT_REGEX,
            message = OrderPaymentConstant.INVALID_AMOUNT)
    @NotNull(message = OrderPaymentConstant.NOT_NULL_AMOUNT)
    private String amount;

    @OneToOne
    @JoinColumn(name = "food_id")
    @NotNull
    private FoodDTO food;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id=" + id +
                ", quantity='" + quantity + '\'' +
                ", amount='" + amount + '\'' +
                ", food=" + food +
                '}';
    }
}