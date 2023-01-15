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

import com.ideas2it.fooddeliveryapp.model.Restaurant;

/**
 * This interface extends JpaRepository for restaurant
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    /**
     * Find all restaurants where isActive is false.
     *
     * @return A list of all restaurants that are not active.
     */
    @Query("SELECT r FROM Restaurant r WHERE r.isDeleted = false")
    List<Restaurant> findAll();

    /**
     * This function deletes a restaurant by setting its
     * isActive field to false.
     *
     * @param id the id of the restaurant to be deleted
     */
    @Modifying
    @Query("UPDATE Restaurant r  SET r.isDeleted = true WHERE r.id = :id")
    void deleteById(int id);
}
