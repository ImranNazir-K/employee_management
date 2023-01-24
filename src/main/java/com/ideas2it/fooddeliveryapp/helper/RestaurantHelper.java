/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.helper;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;
import com.ideas2it.fooddeliveryapp.dto.ReviewDTO;
import com.ideas2it.fooddeliveryapp.model.Restaurant;
import com.ideas2it.fooddeliveryapp.model.Review;

/**
 * Converts DTO Objects into Entity Objects and Entity Objects
 * into DTO Objects using ModelMapper for restaurant and review.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public class RestaurantHelper {

    private static final ModelMapper mapper = new ModelMapper();

    /**
     * Converts a ReviewDTO to Review entity.
     *
     * @param reviewDto The ReviewDTO object.
     * @return A Review object.
     */
    public static Review toReview(ReviewDTO reviewDto) {
        return mapper.map(reviewDto, Review.class);
    }

    /**
     * Converts a Review object to  ReviewDTO object.
     *
     * @param review The Review object.
     * @return A ReviewDTO object.
     */
    public static ReviewDTO toReviewDto(Review review) {
        return mapper.map(review, ReviewDTO.class);
    }

    /**
     * Converts list of reviews to a list of review DTOs.
     *
     * @param reviews The list of reviews.
     * @return A list of ReviewDTO objects.
     */
    public static List<ReviewDTO> toReviewDtos(List<Review> reviews) {
        return reviews.stream().map(review -> mapper.map(review,
                ReviewDTO.class)).toList();
    }

    /**
     * Converts a RestaurantDTO object to a Restaurant object.
     *
     * @param restaurantDto The RestaurantDTO object.
     * @return A Restaurant object.
     */
    public static Restaurant toRestaurant(RestaurantDTO restaurantDto) {
        return mapper.map(restaurantDto, Restaurant.class);
    }

    /**
     * Converts a Restaurant object to a RestaurantDTO object.
     *
     * @param restaurant The Restaurant object.
     * @return A RestaurantDTO object.
     */
    public static RestaurantDTO toRestaurantDto(Restaurant restaurant) {
        return mapper.map(restaurant, RestaurantDTO.class);
    }

    /**
     * Converts a list of restaurants to a list of
     * restaurant DTO.
     *
     * @param restaurants The list of restaurants.
     * @return A list of RestaurantDTO objects.
     */
    public static List<RestaurantDTO> toRestaurantDtos(List<Restaurant>
            restaurants) {
        return restaurants.stream().map(restaurant -> mapper.map(restaurant,
                RestaurantDTO.class)).toList();
    }
}