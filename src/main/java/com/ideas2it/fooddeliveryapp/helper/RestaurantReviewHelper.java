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

public class RestaurantReviewHelper {

    private static final ModelMapper mapper = new ModelMapper();

    /**
     * Convert a ReviewDTO to a Review.
     *
     * @param reviewDto The ReviewDTO object to be
     *                  converted to a Review object.
     * @return A Review object
     */
    public static Review toReview(ReviewDTO reviewDto) {
        return mapper.map(reviewDto, Review.class);
    }

    /**
     * Convert a Review object to a ReviewDTO object.
     *
     * @param review The Review object that we
     *               want to convert to a ReviewDTO object.
     * @return A ReviewDTO object
     */
    public static ReviewDTO toReviewDto(Review review) {
        return mapper.map(review, ReviewDTO.class);
    }

    /**
     * It takes a list of reviews and maps them to a
     * list of review DTOs
     *
     * @param reviews The list of reviews to be
     *                converted to ReviewDTOs.
     * @return A list of ReviewDTOs
     */
    public static List<ReviewDTO> toReviewDtos(List<Review> reviews) {
        return reviews.stream().map(review -> mapper.map(review,
                ReviewDTO.class)).toList();
    }

    /**
     * Convert a RestaurantDTO object to a Restaurant object.
     *
     * @param restaurantDto The RestaurantDTO object
     *                      to be converted to a
     *                      Restaurant object.
     * @return A Restaurant object
     */
    public static Restaurant toRestaurant(RestaurantDTO restaurantDto) {
        return mapper.map(restaurantDto, Restaurant.class);
    }

    /**
     * Convert a Restaurant object to a RestaurantDTO object.
     *
     * @param restaurant The source object to map from.
     * @return A RestaurantDTO object
     */
    public static RestaurantDTO toRestaurantDto(Restaurant restaurant) {
        return mapper.map(restaurant, RestaurantDTO.class);
    }

    /**
     * Convert a list of restaurants to a list of
     * restaurant DTOs.
     *
     * @param restaurants The list of restaurants to
     *                    be converted to a list of
     *                    RestaurantDTOs.
     * @return A list of RestaurantDTOs
     */
    public static List<RestaurantDTO> toRestaurantDtos(List<Restaurant>
            restaurants) {
        return restaurants.stream().map(restaurant -> mapper.map(restaurant,
                RestaurantDTO.class)).toList();
    }
}
