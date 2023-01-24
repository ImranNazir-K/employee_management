/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service;

import java.util.List;

import com.ideas2it.fooddeliveryapp.dto.ReviewDTO;

/**
 * Interface for ReviewServiceImpl to perform CRUD
 * operations for review.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public interface ReviewService {

    /**
     * Creates review for a restaurant.
     *
     * @param reviewDto The review object that needs to be created.
     * @return A ReviewDTO object.
     */
    ReviewDTO createReview(ReviewDTO reviewDto);

    /**
     * Gets all reviews if no reviews are found it return empty list.
     *
     * @return A list of ReviewDTO objects.
     */
    List<ReviewDTO> getReviews();

    /**
     * Gets a review by its id if not found it throws
     * NotFoundException.
     *
     * @param id The id of the review.
     * @return A ReviewDTO object.
     */
    ReviewDTO getReviewById(int id);

    /**
     * Updates a review.
     *
     * @param reviewDto The reviewDto object.
     * @return A ReviewDTO object.
     */
    ReviewDTO updateReview(ReviewDTO reviewDto);

    /**
     * Deletes a review by id if not found it throws
     * NotFoundException.
     *
     * @param id The id of the review.
     * @return true if review deleted.
     */
    boolean deleteReview(int id);

    /**
     * Get all reviews for a restaurant.
     *
     * @param id The id of the restaurant.
     * @return A list of Review for a restaurant.
     */
    List<ReviewDTO> getReviewsByRestaurantId(int id);
}