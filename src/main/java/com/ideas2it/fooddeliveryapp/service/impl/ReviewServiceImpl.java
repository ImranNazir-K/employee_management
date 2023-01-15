/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.RestaurantReviewConstant;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.dto.ReviewDTO;
import com.ideas2it.fooddeliveryapp.helper.RestaurantReviewHelper;
import com.ideas2it.fooddeliveryapp.model.Review;
import com.ideas2it.fooddeliveryapp.repository.ReviewRepository;
import com.ideas2it.fooddeliveryapp.service.ReviewService;

/**
 * This class is a service class that implements
 * the ReviewService interface
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewDTO createReview(ReviewDTO reviewDto) {
        Review review = RestaurantReviewHelper.toReview(reviewDto);
        return RestaurantReviewHelper.toReviewDto(reviewRepository.
                save(review));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReviewDTO> getReviews() {
        return RestaurantReviewHelper.toReviewDtos(reviewRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewDTO getReviewById(int id) {
        Review review = reviewRepository.findById(id)
                .filter(reviewObject -> reviewObject.getIsDeleted() == false)
                .orElseThrow(() -> new NotFoundException(
                        RestaurantReviewConstant.RESTAURANT_NOT_FOUND));
        return RestaurantReviewHelper.toReviewDto(review);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDto) {
        Review review = RestaurantReviewHelper.toReview(reviewDto);
        return RestaurantReviewHelper.toReviewDto(reviewRepository.
                save(review));
    }

    /**
     * {@inheritDoc
     */
    @Override
    public boolean deleteReview(int id) {
        boolean isDeleted = false;

        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            isDeleted = true;
        } else {
            throw new RuntimeException(RestaurantReviewConstant.
                    REVIEW_NOT_FOUND);
        }
        return isDeleted;
    }
}