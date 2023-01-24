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

import com.ideas2it.fooddeliveryapp.dto.FoodDTO;
import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;

/**
 * Interface for FoodServiceImpl class to perform CRUD
 * operations for Food.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
public interface FoodService {

    /**
     * Creates food.
     *
     * @param foodDTO Object of FoodDTO.
     * @return The created FoodDTO object.
     */
    FoodDTO createFood(FoodDTO foodDTO);

    /**
     * Gets all foods.
     *
     * @return List of foods as FoodDTO objects.
     */
    List<FoodDTO> getFoods();

    /**
     * Gets particular food by the given id or throws NotFound
     * exception if the given id is not present.
     *
     * @param id The id of the particular food.
     * @return FoodDTO object.
     */
    FoodDTO getFoodById(int id);

    /**
     * Updates food details.
     *
     * @param foodDTO Object of FoodDTO.
     * @return The updated FoodDTO object.
     */
    FoodDTO updateFood(FoodDTO foodDTO);

    /**
     * Deletes food by id or throws NotFound exception if the given
     * id is not found.
     *
     * @param id The id of the food to be deleted.
     * @return true if deleted.
     */
    boolean deleteFood(int id);

    /**
     * Gets the restaurants based on the given food name.
     *
     * @param foodName Name of the food which is to be checked if
     *                 it is present in the restaurants.
     * @return List of restaurants containing the given food name.
     */
    List<RestaurantDTO> getRestaurantByFood(String foodName);

    /**
     * Gets foods of a particular category in a particular
     * restaurant.
     *
     * @param restaurantId The id of the restaurant.
     * @param category     The category of food.
     * @return List of FoodDTO objects.
     */
    List<FoodDTO> getFoodByCategory(int restaurantId, String category);

    /**
     * Gets foods of a particular cuisine in a particular
     * restaurant.
     *
     * @param restaurantId The id of the restaurant.
     * @param cuisine      The cuisine of food.
     * @return List of FoodDTO objects.
     */
    List<FoodDTO> getFoodByCuisine(int restaurantId, String cuisine);
}