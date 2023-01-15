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

import com.ideas2it.fooddeliveryapp.dto.FoodDTO;
import com.ideas2it.fooddeliveryapp.service.FoodService;

/**
 * Controller class for Food entity
 *
 * @author M Mohamed Riyas
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * Inserts food into record
     *
     * @param foodDTO DTO object of food entity
     *
     * @return DTO object of food entity
     */
    @PostMapping
    public FoodDTO createFood(
            @Valid @RequestBody FoodDTO foodDTO) {
        return foodService.createFood(foodDTO);
    }

    /**
     * Gets all deliveries from the record
     *
     * @return List of all deliveries from the records as DTO objects
     */
    @GetMapping
    public List<FoodDTO> getFoods() {
        return foodService.getFoods();
    }

    /**
     * Gets particular food from the record by id
     *
     * @param id id of food to be fetched
     *
     * @return DTO object of food entity
     */
    @GetMapping("/{id}")
    public FoodDTO getFoodById(@PathVariable int id) {
        return foodService.getFoodById(id);
    }

    /**
     * Updates food details from the record
     *
     * @param foodDTO DTO object of food entity
     *
     * @return updated DTO object of food entity
     */
    @PutMapping
    public FoodDTO updateFood(@Valid @RequestBody
            FoodDTO foodDTO) {
        return foodService.updateFood(foodDTO);
    }

    /**
     * Deletes food from the record by id
     *
     * @param id id of the food to be deleted
     *
     * @return message to be displayed after deleting as String
     */
    @DeleteMapping("/{id}")
    public boolean deleteFood(@PathVariable int id) {
        return foodService.deleteFood(id);
    }
}

