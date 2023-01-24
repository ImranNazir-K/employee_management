/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.ReviewDTO;
import com.ideas2it.fooddeliveryapp.service.ReviewService;

/**
 * Controller class for Handling the incoming requests to validate
 * the user input for performing crud operations for Review.
 *
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Creates review for restaurant.
     *
     * @param reviewDto  The reviewDto object that will be
     *                   created.
     * @return A ReviewDTO object which was created.
     */
    @PostMapping
    public ReviewDTO createReview(@RequestBody @Valid ReviewDTO reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    /**
     * Gets all reviews if no reviews are found it return empty list.
     *
     * @return A list of ReviewDTO objects.
     */
    @GetMapping
    public List<ReviewDTO> getReviews() {
        return reviewService.getReviews();
    }

    /**
     * Gets a review by its id if not found it throws
     * NotFoundException.
     *
     * @param id The id of the review you want to get.
     * @return A ReviewDTO object.
     */
    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    /**
     * Updates a review.
     *
     * @param reviewDto The reviewDto object that will be updated.
     * @return A ReviewDTO object which was updated.
     */
    @PutMapping
    public ReviewDTO updateReview(@RequestBody @Valid ReviewDTO reviewDto) {
        return reviewService.updateReview(reviewDto);
    }

    /**
     * Deletes a review by id if not found it throws
     * NotFoundException.
     *
     * @param id The id of the review.
     * @return true if review was deleted.
     */
    @DeleteMapping("/{id}")
    public boolean deleteReview(@PathVariable int id) {
        return reviewService.deleteReview(id);
    }
}