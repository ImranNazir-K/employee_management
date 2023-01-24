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

import com.ideas2it.fooddeliveryapp.constant.FoodConstant;

/**
 * Data Transfer Object for the Delivery.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
public class DeliveryDTO {

    private int id;

    @NotNull(message = FoodConstant.PICKUP_LOCATION_NOT_NULL)
    private AddressDTO pickupLocation;

    @NotNull(message = FoodConstant.DROP_LOCATION_NOT_NULL)
    private AddressDTO dropLocation;

    @NotNull(message = FoodConstant.USER_NOT_NULL)
    private UserResponseDTO userDTO;

    @NotNull(message = FoodConstant.ORDER_NOT_NULL)
    private OrderDetailDTO orderDetailDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddressDTO getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(AddressDTO pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public AddressDTO getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(AddressDTO dropLocation) {
        this.dropLocation = dropLocation;
    }

    public UserResponseDTO getUser() {
        return userDTO;
    }

    public void setUser(UserResponseDTO userDTO) {
        this.userDTO = userDTO;
    }

    public OrderDetailDTO getOrderDetail() {
        return orderDetailDTO;
    }

    public void setOrderDetail(OrderDetailDTO orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
    }
}