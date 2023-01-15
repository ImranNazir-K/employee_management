/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import jakarta.validation.constraints.Pattern;

import com.ideas2it.fooddeliveryapp.constant.RestaurantReviewConstant;
import com.ideas2it.fooddeliveryapp.util.Experience;
import com.ideas2it.fooddeliveryapp.util.Rating;

/**
 * Contains Review fields as rating,review in private
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
public class ReviewDTO {

    private int id;
    private Rating rating;
    private Experience experience;
    @Pattern(regexp = RestaurantReviewConstant.REMARK_REGEX,
            message = RestaurantReviewConstant.INVALID_REMARK)
    private String feedback;
    private RestaurantDTO restaurant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }
}
