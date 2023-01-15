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

import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;
import com.ideas2it.fooddeliveryapp.service.RestaurantService;

/**
 * This class is a controller class for the restaurant in food
 * delivery app
 * Performs operations like create, Display, Update, Delete
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * Creates restaurant
     *
     * @param restaurantDto The object that will be created.
     * @return A RestaurantDTO object
     */
    @PostMapping()
    public RestaurantDTO createRestaurant(@RequestBody @Valid RestaurantDTO
            restaurantDto) {
        return restaurantService.createRestaurant(restaurantDto);
    }

    /**
     * It returns a list of all restaurants.
     *
     * @return A list of RestaurantDTO objects
     */
    @GetMapping()
    public List<RestaurantDTO> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    /**
     * It takes in an id, and returns a restaurantDTO
     *
     * @param id The id of the restaurant to be retrieved.
     * @return A RestaurantDTO object
     */
    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable int id) {
        return restaurantService.getRestaurantById(id);
    }

    /**
     * Its takes in a RestaurantDTO object, and returns
     * a RestaurantDTO object
     *
     * @param restaurantDto The object that will be updated.
     * @return A RestaurantDTO object
     */
    @PutMapping
    public RestaurantDTO updateRestaurant(@RequestBody @Valid RestaurantDTO
            restaurantDto) {
        return restaurantService.updateRestaurant(restaurantDto);
    }

    /**
     * It deletes a restaurant by id.
     *
     * @param id The id of the restaurant to be deleted.
     * @return A string
     */
    @DeleteMapping("/{id}")
    public boolean deleteRestaurant(@PathVariable int id) {
        return restaurantService.deleteRestaurant(id);
    }
}