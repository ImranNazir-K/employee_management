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

import com.ideas2it.fooddeliveryapp.constant.FoodConstant;

/**
 * Data Transfer Object for Food.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
public class FoodDTO {

    private int id;

    @NotNull(message = FoodConstant.NAME_NOT_NULL)
    @Pattern(regexp = FoodConstant.FOOD_NAME_REGEX,
            message = FoodConstant.INVALID_NAME)
    private String name;

    private Boolean isAvailable;

    @NotNull(message = FoodConstant.PRICE_NOT_NULL)
    private float price;

    @Pattern(regexp = FoodConstant.DESCRIPTION_REGEX,
            message = FoodConstant.INVALID_DESCRIPTION)
    private String description;

    @Pattern(regexp = FoodConstant.CUISINE_CATEGORY_REGEX,
            message = FoodConstant.INVALID_CUISINE)
    private String cuisine;

    @Pattern(regexp = FoodConstant.CUISINE_CATEGORY_REGEX,
            message = FoodConstant.INVALID_CATEGORY)
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}