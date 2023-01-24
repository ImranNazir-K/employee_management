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
 * methods for CRUD operations.
 *
 * @author - Govindaraj
 * @version - 1.0
 * @since 04/01/2023
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Gets a list of user where isDeleted = false.
     *
     * @return list of user.
     */
    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    List<User> findAll();

    /**
     * Deletes a specific user by its id and updates its isDeleted
     * to true.
     *
     * @param id of the user.
     */
    @Modifying
    @Query("UPDATE User u SET isDeleted = true WHERE u.id = :id")
    void deleteById(int id);

    /**
     * Gets a User by its mailId.
     *
     * @param email The MailId of user.
     * @return User object.
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByMailId(String email);

    /**
     * Gets a User by its userName.
     *
     * @param userName The MailId of user.
     * @return User object.
     */
    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    User findByUserName(String userName);

    /**
     * Gets a User by its phone number.
     *
     * @param contactNumber The phone number of the user.
     * @return A User object
     */
    @Query("SELECT u FROM User u WHERE u.phoneNumber = :contactNumber")
    User findByPhoneNumber(String contactNumber);
}