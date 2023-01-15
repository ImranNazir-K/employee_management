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
 * This interface is for ReviewServiceImpl
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
public interface ReviewService {


    /**
     * Creates a new review for a restaurant.
     *
     * @param reviewDto The review object that
     *                  needs to be created.
     * @return ReviewDTO
     */
    ReviewDTO createReview(ReviewDTO reviewDto);


    /**
     * Gets all reviews.
     *
     * @return A list of ReviewDTO objects.
     */
    List<ReviewDTO> getReviews();


    /**
     * Gets a review by its id.
     *
     * @param id The id of the review you want to get.
     * @return A ReviewDTO object.
     */
    ReviewDTO getReviewById(int id);

    /**
     * Update a review.
     *
     * @param reviewDto The review object that
     *                  needs to be updated.
     * @return ReviewDTO
     */
    ReviewDTO updateReview(ReviewDTO reviewDto);


    /**
     * Delete a review by id.
     *
     * @param id The id of the review you want to delete.
     * @return A string.
     */
    boolean deleteReview(int id);
}
