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

import com.ideas2it.fooddeliveryapp.model.Address;
import com.ideas2it.fooddeliveryapp.model.OrderDetail;
import com.ideas2it.fooddeliveryapp.model.User;
import com.ideas2it.fooddeliveryapp.util.DeliveryStatus;

public class DeliveryDTO {
    private int id;
    private Address pickupLocation;
    private Address dropLocation;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private User user;
    private OrderDetail orderDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Address pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Address getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(Address dropLocation) {
        this.dropLocation = dropLocation;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}