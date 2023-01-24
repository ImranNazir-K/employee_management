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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.ideas2it.fooddeliveryapp.constant.RestaurantConstant;
import com.ideas2it.fooddeliveryapp.util.Experience;
import com.ideas2it.fooddeliveryapp.util.Rating;

/**
 * Data Transfer Object for the class Review that consists of
 * variables like id, rating, experience, feedback, user, restaurant
 * with setters and getters.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public class ReviewDTO {

    private int id;

    @NotNull(message = RestaurantConstant.RATING_NOT_NULL)
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @NotNull(message = RestaurantConstant.EXPERIENCE_NOT_NULL)
    @Enumerated(EnumType.STRING)
    private Experience experience;

    @Pattern(regexp = RestaurantConstant.FEEDBACK_REGEX,
            message = RestaurantConstant.INVALID_FEEDBACK)
    private String feedback;

    @NotNull(message = RestaurantConstant.USER_NOT_NULL)
    private UserResponseDTO user;

    @NotNull(message = RestaurantConstant.RESTAURANT_NOT_NULL)
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

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }
}