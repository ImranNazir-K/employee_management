/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.fooddeliveryapp.model.Review;

/**
 * Repository for Review that extends JpaRepository and provides
 * additional custom methods to performs CRUD operations.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    /**
     * Gets all reviews where isDeleted field is false.
     *
     * @return A list of all Review.
     */
    @Query("SELECT r FROM Review r WHERE r.isDeleted = false")
    List<Review> findAll();


    /**
     * Deletes the particular review by updating the isDeleted
     * field as true.
     *
     * @param id the id of the review to be deleted
     */
    @Modifying
    @Query("UPDATE Review r SET r.isDeleted = true WHERE r.id = :id")
    void deleteById(int id);

    /**
     * Find all reviews for a restaurant with the given id.
     *
     * @param id The id of the restaurant.
     * @return A list of Review for a restaurant.
     */
    List<Review> findAllByRestaurantId(int id);
}