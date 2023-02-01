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

import com.ideas2it.fooddeliveryapp.dto.JwtRequestDTO;
import com.ideas2it.fooddeliveryapp.dto.UserDTO;
import com.ideas2it.fooddeliveryapp.dto.UserResponseDTO;

/**
 * Interface for UserServiceImpl class to perform CRUD
 * operations for User.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
public interface UserService {

    /**
     * Creates user by checking the emailId and phoneNumber to avoid
     * duplication if found it throws DuplicateFoundException.
     *
     * @param userDto the user object.
     * @return userResponseDto object which was created.
     */
    UserResponseDTO createUser(UserDTO userDto);

    /**
     * Gets a user by its id and if not found it throws
     * NotFoundException.
     *
     * @param id The id of user.
     * @return userResponseDTO object.
     */
    UserResponseDTO getUserById(int id);

    /**
     * Gets all the users as list if no users are found it return
     * empty list.
     *
     * @return list of userResponseDto object.
     */
    List<UserResponseDTO> getAllUser();

    /**
     * Updates user by checking the emailId and phoneNumber to avoid
     * duplication if found it throws DuplicateFoundException.
     *
     * @param userDto the UserDTO object.
     * @return A UserResponseDTO which was updated.
     */
    UserResponseDTO updateUser(UserDTO userDto);

    /**
     * Deletes a user by id.
     *
     * @param id The id of the user to delete.
     * @return true if user delete.
     */
    boolean deleteUserById(int id);

    /**
     * Checks whether the contact is already exist for other user
     * to avoid duplication, if found it throws
     * DuplicateFoundException.
     *
     * @param phoneNumber for redundancy validation.
     */
    void isPhoneNumberExists(String phoneNumber);

    /**
     * Checks whether the email is already exist for other user to
     * avoid duplication, if found it throws
     * DuplicateFoundException.
     *
     * @param email for redundancy validation.
     */
    void isEmailExists(String email);

    /**
     * Checks whether the email is already exist for other user to
     * avoid duplication while updating user, if found throws
     * DuplicateFoundException.
     *
     * @param id    of the user.
     * @param email for redundancy validation.
     * @return true if contact is exist else false.
     */
    boolean isEmailAlreadyExists(int id, String email);

    /**
     * Checks whether the contact is already exist for other user
     * to avoid duplication while updating user, if found throws
     * DuplicateFoundException.
     *
     * @param id          of the user.
     * @param phoneNumber phoneNumber for validation.
     * @return true if contact is exist else false.
     */
    boolean isPhoneNumberAlreadyExists(int id, String phoneNumber);

    /**
     * JwtRequest object as input, validate it,
     * and returns a JwtResponseDTO object.
     *
     * @param jwtRequest request object.
     *                   that contains the username and password.
     * @return String JWT token .
     */
    String generateToken(JwtRequestDTO jwtRequest);
}