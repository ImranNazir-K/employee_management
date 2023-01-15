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

import com.ideas2it.fooddeliveryapp.constant.RestaurantReviewConstant;

/**
 * Contains Restaurant fields as name,phone number,
 * id,emailId,description in private.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
public class RestaurantDTO {

    private int id;
    @NotNull(message = RestaurantReviewConstant.NAME_NOTNULL)
    @Pattern(regexp = RestaurantReviewConstant.RESTAURANT_NAME_REGEX,
            message = RestaurantReviewConstant.INVALID_NAME)
    private String name;
    @NotNull(message = RestaurantReviewConstant.PHONENUMBER_NOTNULL)
    @Pattern(regexp = RestaurantReviewConstant.PHONENUMBER_REGEX,
            message = RestaurantReviewConstant.
                    INVALID_PHONENUMBER)
    private String phoneNumber;
    @NotNull(message = RestaurantReviewConstant.EMAIL_NOTNULL)
    @Pattern(regexp = RestaurantReviewConstant.EMAIL_REGEX,
            message = RestaurantReviewConstant.INVALID_EMAIL)
    private String email;
    @Pattern(regexp = RestaurantReviewConstant.DESCRIPTION_REGEX,
            message = RestaurantReviewConstant.
                    INVALID_DESCRIPTION)
    private String description;
    private List<ReviewDTO> review;

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

    public List<ReviewDTO> getReview() {
        return review;
    }

    public void setReview(List<ReviewDTO> review) {
        this.review = review;
    }
}
