/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.ideas2it.fooddeliveryapp.constant.OrderConstant;

/**
 * Data Transfer Object class for OrderItem that consists of fields
 * like id, quantity, amount and FoodDTO with getters and setters.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
public class OrderItemDTO {

    private int id;

    @Pattern(regexp = OrderConstant.QUANTITY_REGEX,
            message = OrderConstant.INVALID_QUANTITY)
    @NotNull(message = OrderConstant.NOT_NULL_QUANTITY)
    private String quantity;

    private String amount;

    @NotNull(message = OrderConstant.NOT_NULL_FOOD)
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
}