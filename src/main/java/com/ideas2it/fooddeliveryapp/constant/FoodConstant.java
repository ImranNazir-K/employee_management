/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.constant;

/**
 * Contains Constants for Food and Delivery.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
public class FoodConstant {

    public static final String CUISINE_CATEGORY_REGEX = "((([A-Za-z]{2,}"
            + "([ ]?)){1,}))((([-]?)([a-zA-Z]{1})){2,}){1,50}";
    public static final String DELIVERY_NOT_FOUND = "Delivery ID not found";
    public static final String DESCRIPTION_REGEX = "((([A-Za-z]{1,}([ ]?))"
            + "{1,}))((([.]?)([a-zA-Z]{0,})){1,100})";
    public static final String DROP_LOCATION_NOT_NULL = "Drop location "
            + "cannot be null";
    public static final String FOOD_NAME_REGEX = "((([A-Za-z]{2,}"
            + "([ ]?)){1,}))(([-]?)([a-zA-Z0-9]{1,}))";
    public static final String FOOD_NOT_FOUND = "Food ID not found";
    public static final String INVALID_CATEGORY = "Invalid Category Name "
            + "(or) Maximum characters reached(50)!";
    public static final String INVALID_CUISINE = "Invalid Cuisine Name "
            + "(or) Maximum characters reached(50)!";
    public static final String INVALID_DESCRIPTION = "Invalid Description "
            + "(or) Maximum characters reached(100)!";
    public static final String INVALID_NAME = "Invalid Food Name Format!";
    public static final String NAME_NOT_NULL = "Name cannot be null";
    public static final String ORDER_NOT_NULL = "Order cannot be null";
    public static final String PICKUP_LOCATION_NOT_NULL = "Pickup location "
            + "cannot be null";
    public static final String PRICE_NOT_NULL = "Price cannot be null";
    public static final String USER_NOT_NULL = "User cannot be null";
}