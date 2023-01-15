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
 * This interface extends JpaRepository for restaurant
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    /**
     * This function will return a list of all reviews
     * that are not active
     *
     * @return A list of all reviews that are not active.
     */
    @Query("SELECT r FROM Review r WHERE r.isDeleted = false")
    List<Review> findAll();

    /**
     * Set the isActive field to true for the Review
     * with the given id.
     *
     * @param id the id of the review to be deleted
     */
    @Modifying
    @Query("UPDATE Review r SET r.isDeleted = true WHERE r.id = :id")
    void deleteById(int id);
}