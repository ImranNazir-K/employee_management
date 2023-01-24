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

import com.ideas2it.fooddeliveryapp.dto.JwtRequestDTO;
import com.ideas2it.fooddeliveryapp.dto.UserDTO;
import com.ideas2it.fooddeliveryapp.dto.UserResponseDTO;
import com.ideas2it.fooddeliveryapp.service.UserService;

/**
 * Controller class for the Food Delivery Application that does
 * CRUD operations for User.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates user by checking the emailId and phoneNumber to avoid
     * duplication if found it throws DuplicateFoundException.
     *
     * @param userDto as UserDTO instance consists a user.
     * @return userResponseDto which was created else throws exception.
     */
    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserDTO userDto) {
        return userService.createUser(userDto);
    }

    /**
     * Gets a user by its id and if not found it throws
     * NotFoundException.
     *
     * @param id the id of a user.
     * @return UserResponseDTO instance that consists a user.
     */
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    /**
     * Gets all the users that are available else returns empty list.
     *
     * @return List<UserResponseDTO> list of user details.
     */
    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return userService.getAllUser();
    }

    /**
     * Updates user by checking the emailId and phoneNumber to avoid
     * duplication if found it throws DuplicateFoundException.
     *
     * @param userDto The userDto object.
     * @return updated userResponseDto object.
     */
    @PutMapping
    public UserResponseDTO updateUser(@Valid @RequestBody UserDTO userDto) {
        return userService.updateUser(userDto);
    }

    /**
     * Deletes user by id.
     *
     * @param id The id of the user.
     * @return true if deleted.
     */
    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

    /**
     * Builds a Json web token for the user. The json web token is
     * build by setting the claims and digitally signed with
     * algorithm and a secret by compacting it into a URL-safe
     * JWT String.
     *
     * @param jwtRequest as JwtRequestDTO object contains
     *                   username and password.
     * @return A compact URL-safe JWT string.
     */
    @PostMapping("/authenticate")
    public String authenticate(@Valid @RequestBody JwtRequestDTO jwtRequest) {
        return userService.generateToken((jwtRequest));
    }
}