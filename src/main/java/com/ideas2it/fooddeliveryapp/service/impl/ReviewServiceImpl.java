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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.RestaurantConstant;
import com.ideas2it.fooddeliveryapp.dto.ReviewDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.RestaurantHelper;
import com.ideas2it.fooddeliveryapp.model.Review;
import com.ideas2it.fooddeliveryapp.repository.ReviewRepository;
import com.ideas2it.fooddeliveryapp.service.ReviewService;

/**
 * Service class for review that implements ReviewService
 * to perform CRUD operations.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LoggerFactory
            .getLogger(RestaurantServiceImpl.class);

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewDTO createReview(ReviewDTO reviewDto) {
        Review review = reviewRepository.save(RestaurantHelper
                .toReview(reviewDto));
        logger.info("Review Created");
        return RestaurantHelper.toReviewDto(review);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReviewDTO> getReviews() {
        return RestaurantHelper.toReviewDtos(reviewRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewDTO getReviewById(int id) {
        Review review = reviewRepository.findById(id)
                .filter(reviewObject -> !reviewObject.getIsDeleted())
                .orElseThrow(() -> {
                    logger.warn("Restaurant id not found");
                    throw new NotFoundException(RestaurantConstant
                            .RESTAURANT_NOT_FOUND);
                });

        logger.info("Gets the particular review");
        return RestaurantHelper.toReviewDto(review);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDto) {
        Review review = reviewRepository.save(RestaurantHelper
                .toReview(reviewDto));
        logger.info("Review Updated");
        return RestaurantHelper.toReviewDto(review);
    }

    /**
     * {@inheritDoc
     */
    @Override
    public boolean deleteReview(int id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            logger.info("Review Deleted");
            return true;
        } else {
            throw new NotFoundException(RestaurantConstant.REVIEW_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc
     */
    @Override
    public List<ReviewDTO> getReviewsByRestaurantId(int id) {
        return RestaurantHelper.toReviewDtos(reviewRepository
                .findAllByRestaurantId(id));
    }
}