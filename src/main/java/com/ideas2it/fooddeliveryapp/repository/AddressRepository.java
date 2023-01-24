/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.fooddeliveryapp.model.Address;

/**
 * Interface extends jpaRepository which provides
 * methods for CRUD operations.
 *
 * @author - Govindaraj
 * @version - 1.0
 * @since 04/01/2023
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {

    /**
     * Deletes a specific address by using its id and
     * updates isDeleted to true.
     *
     * @param id the address id as int.
     */
    @Modifying
    @Query("UPDATE Address a SET isDeleted = true WHERE a.id = :id")
    void deleteById(int id);
}