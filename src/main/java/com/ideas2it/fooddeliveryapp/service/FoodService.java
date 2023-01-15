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

/**
 * Service class for Food entity
 *
 * @author M Mohamed Riyas
 *
 * @version 1.0
 */
public interface FoodService {

    /**
     * Inserts food into record
     *
     * @param foodDTO DTO object of food entity
     *
     * @return DTO object of food entity
     */
    FoodDTO createFood(FoodDTO foodDTO);

    /**
     * Gets all foods from the record
     *
     * @return List of all foods from the records as DTO objects
     */
    List<FoodDTO> getFoods();

    /**
     * Gets particular food from the record by id
     *
     * @param id id of food to be fetched
     *
     * @return DTO object of food entity
     */
    FoodDTO getFoodById(int id);

    /**
     * Updates delivery details from the record
     *
     * @param foodDTO DTO object of delivery entity
     *
     * @return updated DTO object of food entity
     */
    FoodDTO updateFood(FoodDTO foodDTO);

    /**
     * Deletes food from the record by id
     *
     * @param id id of the food to be deleted
     *
     * @return message to be displayed after deleting as String
     */
    boolean deleteFood(int id);
}
