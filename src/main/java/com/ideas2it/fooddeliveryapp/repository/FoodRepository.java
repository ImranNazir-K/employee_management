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

import com.ideas2it.fooddeliveryapp.model.Food;

/**
 * Repository for Food that extends JpaRepository and provides
 * additional custom methods to perform CRUD operations.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
public interface FoodRepository extends JpaRepository<Food, Integer> {

    /**
     * Gets all foods where isDeleted field is false.
     *
     * @return List of all foods.
     */
    @Query("SELECT f from Food f where f.isDeleted = false")
    List<Food> findAll();

    /**
     * Deletes the particular food by updating the isDeleted field
     * as true.
     *
     * @param id The id of the food to be deleted.
     */
    @Modifying
    @Query(value = "UPDATE Food f SET f.isDeleted = true WHERE f.id = :id")
    void deleteById(int id);
}