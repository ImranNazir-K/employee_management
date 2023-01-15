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

import com.ideas2it.fooddeliveryapp.model.User;

/**
 * Interface extends jpaRepository which provides
 * methods deals with CRUD operations.
 *
 * @author - Govindaraj
 * @version - 1.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Returns a list of user by checking its isDeleted status.
     *
     * @return list of user.
     */
    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    List<User> findAll();

    /**
     * Deletes a specific object by using its id and
     * updates isDeleted into true.
     *
     * @param id for object reference.
     */
    @Modifying
    @Query("UPDATE User u SET isDeleted = true WHERE u.id = :id")
    void deleteById(int id);
}