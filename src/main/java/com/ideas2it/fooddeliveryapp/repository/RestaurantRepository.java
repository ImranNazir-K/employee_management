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
 * Repository for Restaurant that extends JpaRepository and provides
 * additional custom methods to perform CRUD operations.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public interface RestaurantRepository extends JpaRepository<Restaurant,
        Integer> {

    /**
     * Gets all restaurant where isDeleted field is false.
     *
     * @return A list of all Restaurant
     */
    @Query("SELECT r FROM Restaurant r WHERE r.isDeleted = false")
    List<Restaurant> findAll();


    /**
     * Deletes the particular restaurant by updating the isDeleted
     * field as true.
     *
     * @param id the id of the restaurant to be deleted
     */
    @Modifying
    @Query("UPDATE Restaurant r SET r.isDeleted = true WHERE r.id = :id")
    void deleteById(int id);

    /**
     * Gets a restaurant by its phone number.
     *
     * @param phoneNumber The phone number of the restaurant.
     * @return A Restaurant object.
     */
    Restaurant findByPhoneNumber(String phoneNumber);

    /**
     * Gets a restaurant by its email.
     *
     * @param email The email of the restaurant.
     * @return A Restaurant object.
     */
    @Query("SELECT r from Restaurant r where r.email = :email")
    Restaurant getByEmail(String email);

    /**
     * Find all restaurants in the given area.
     *
     * @param areaName The area name of the restaurant.
     * @return A list of Restaurant that are in the area specified.
     */
    List<Restaurant> findAllByAddressArea(String areaName);

    /**
     * Find all restaurants that serve a given cuisine.
     *
     * @param cuisineName The name of the cuisine.
     * @return A list of Restaurant objects that serve the cuisine.
     */
    List<Restaurant> findAllByFoodsCuisine(String cuisineName);

    /**
     * Find all restaurants that have food with the given name.
     *
     * @param foodName The name of the food.
     * @return A list of Restaurant objects.
     */
    List<Restaurant> findAllByFoodsName(String foodName);
}