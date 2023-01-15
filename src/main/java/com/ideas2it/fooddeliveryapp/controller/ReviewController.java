/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.controller;

import java.util.List;

import jakarta.validation.Valid;
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
 * This class is a controller class for the review in food
 * delivery app
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Creates review
     *
     * @param reviewDto This is the object that will be passed
     *                  in the request body.
     * @return ReviewDTO
     */
    @PostMapping()
    public ReviewDTO createReview(@RequestBody @Valid ReviewDTO reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    /**
     * Gets a list of all the reviews
     *
     * @return A list of ReviewDTOs
     */
    @GetMapping()
    public List<ReviewDTO> getReviews() {
        return reviewService.getReviews();
    }

    /**
     * Takes in an id, and returns a ReviewDTO
     *
     * @param id The id of the review you want to get.
     * @return A ReviewDTO object
     */
    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    /**
     * updates a review.
     *
     * @param reviewDto The object that will be updated.
     * @return ReviewDTO
     */
    @PutMapping
    public ReviewDTO updateReview(@RequestBody @Valid ReviewDTO reviewDto) {
        return reviewService.updateReview(reviewDto);
    }

    /**
     * Deletes a review
     *
     * @param id The id of the review you want to delete.
     * @return A string
     */
    @DeleteMapping("/{id}")
    public boolean deleteReview(@PathVariable int id) {
        return reviewService.deleteReview(id);
    }
}

