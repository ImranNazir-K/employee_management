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
 * Class contains constants for OrderDetail, Order Item and Payment.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 * @since 04/01/2023
 */
public class OrderConstant {

    public static final String INVALID_QUANTITY = "Please Enter a valid "
            + "Quantity";
    public static final String NOT_NULL_FOOD = "Food should not be null";
    public static final String NOT_NULL_PAYMENT_MODE = "Payment Mode should not"
            + " be null";
    public static final String NOT_NULL_PAYMENT_STATUS = "Payment Mode should "
            + "not be null";
    public static final String NOT_NULL_QUANTITY = "Quantity should not be "
            + "null";
    public static final String NOT_NULL_RESTAURANT = "Restaurant should not be "
            + "null";
    public static final String NOT_NULL_USER = "User cannot be null";
    public static final String ORDER_DETAIL_NOT_FOUND = "Order Not found";
    public static final String PAYMENT_NOT_NULL = "Payment cannot be null";
    public static final String QUANTITY_REGEX = "([1-9]{1})(([0-9])*)";
}