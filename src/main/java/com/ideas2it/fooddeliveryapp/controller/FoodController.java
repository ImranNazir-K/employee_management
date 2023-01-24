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

import com.ideas2it.fooddeliveryapp.dto.FoodDTO;
import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;
import com.ideas2it.fooddeliveryapp.service.FoodService;

/**
 * Controller class for handling the incoming requests to validate
 * the user input for performing CRUD operations for Food.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * Creating the food from the given data.
     *
     * @param foodDTO the object of FoodDTO.
     * @return FoodDTO object.
     */
    @PostMapping
    public FoodDTO createFood(@Valid @RequestBody FoodDTO foodDTO) {
        return foodService.createFood(foodDTO);
    }

    /**
     * Gets all the foods as list of foods, if it is not soft
     * deleted.
     *
     * @return List of all foods as FoodDTO objects.
     */
    @GetMapping
    public List<FoodDTO> getFoods() {
        return foodService.getFoods();
    }

    /**
     * Gets a particular food by id, if it is not soft deleted or
     * throws NotFound exception if the id is not present.
     *
     * @param id the id of food to be fetched.
     * @return FoodDTO object.
     */
    @GetMapping("/{id}")
    public FoodDTO getFoodById(@PathVariable int id) {
        return foodService.getFoodById(id);
    }

    /**
     * Update the food details with the given updated data.
     *
     * @param foodDTO DTO object of food entity.
     * @return updated FoodDTO object.
     */
    @PutMapping
    public FoodDTO updateFood(@Valid @RequestBody FoodDTO foodDTO) {
        return foodService.updateFood(foodDTO);
    }

    /**
     * Soft deleting a particular food by the given id or throws
     * NotFound exception if the id is not present.
     *
     * @param id The id of the food to be deleted.
     * @return true if deleted.
     */
    @DeleteMapping("/{id}")
    public boolean deleteFood(@PathVariable int id) {
        return foodService.deleteFood(id);
    }

    /**
     * Gets all the restaurants as list of restaurants containing
     * the given food name, if it is soft deleted.
     *
     * @param foodName Name of the food.
     * @return List of restaurants containing the given food.
     */
    @GetMapping("/{foodName}/restaurants")
    public List<RestaurantDTO> getRestaurantByFood(@PathVariable
    String foodName) {
        return foodService.getRestaurantByFood(foodName);
    }

    /**
     * Gets all the foods as list of foods of a particular
     * category in a particular restaurant, if it is not
     * soft deleted.
     *
     * @param restaurantId The id of the restaurant.
     * @param category     The category of food.
     * @return List of FoodDTO objects.
     */
    @GetMapping("/category/{category}/restaurants/{restaurantId}")
    public List<FoodDTO> getFoodByCategory(@PathVariable int restaurantId,
            @PathVariable String category) {
        return foodService.getFoodByCategory(restaurantId, category);
    }

    /**
     * Gets all the foods as list of foods of a particular cuisine
     * in a particular restaurant.
     *
     * @param restaurantId The id of the restaurant.
     * @param cuisine      The cuisine of food.
     * @return List of FoodDTO objects.
     */
    @GetMapping("/cuisine/{cuisine}/restaurants/{restaurantId}")
    public List<FoodDTO> getFoodByCuisine(@PathVariable int restaurantId,
            @PathVariable String cuisine) {
        return foodService.getFoodByCuisine(restaurantId, cuisine);
    }
}