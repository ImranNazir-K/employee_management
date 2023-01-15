/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.constant;

public class FoodConstant {

    public static final String CUISINE_CATEGORY_REGEX = "((([A-Za-z]{2,}"
            + "([ ]?)){1,}))((([-]?)([a-zA-Z]{1})){2,}){1,50}";
    public static final String DESCRIPTION_REGEX = "((([A-Za-z]{1,}([ ]?))"
            + "{1,}))((([.]?)([a-zA-Z]{0,})){1,100})";
    public static final String DELIVERY_NOT_FOUND = "Delivery ID not found";
    public static final String FOOD_NAME_REGEX = "((([A-Za-z]{2,}"
            + "([ ]?)){1,}))(([-]?)([a-zA-Z0-9]{1,}))";
    public static final String FOOD_NOT_FOUND = "Food ID not found";

}
