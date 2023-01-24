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
import java.util.List;

import com.ideas2it.fooddeliveryapp.constant.RestaurantConstant;

/**
 * Data Transfer Object for the class Restaurant that consists of
 * variables like id, name, phone number, email, description, address
 * and food with setters and getters.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public class RestaurantDTO {

    private int id;

    @NotNull(message = RestaurantConstant.RESTAURANT_NAME_NOT_NULL)
    @Pattern(regexp = RestaurantConstant.RESTAURANT_NAME_REGEX,
            message = RestaurantConstant.INVALID_NAME)
    private String name;

    @NotNull(message = RestaurantConstant.PHONE_NUMBER_NOT_NULL)
    @Pattern(regexp = RestaurantConstant.PHONE_NUMBER_REGEX,
             message = RestaurantConstant.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotNull(message = RestaurantConstant.EMAIL_NOT_NULL)
    @Pattern(regexp = RestaurantConstant.EMAIL_REGEX,
             message = RestaurantConstant.INVALID_EMAIL)
    private String email;

    @Pattern(regexp = RestaurantConstant.DESCRIPTION_REGEX,
             message = RestaurantConstant.INVALID_DESCRIPTION)
    private String description;

    private AddressDTO address;

    private List<FoodDTO> foods;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }
}