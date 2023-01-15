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
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.AddressDTO;
import com.ideas2it.fooddeliveryapp.dto.UserDTO;
import com.ideas2it.fooddeliveryapp.service.UserService;

/**
 * This is controller class for user entity and
 * provides methods for CRUD operations.
 *
 * @author Govindaraj
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * It takes a UserDTO object as a request body and calls
     * create function in user service if user is created
     * it return user object else it throws exception.
     *
     * @param userDto The user object is to be added.
     * @return give user object as response.
     */
    @PostMapping()
    public UserDTO createUser(@RequestBody UserDTO userDto) {
        Set<AddressDTO> address = userDto.getAddressList();
        return userService.createUser(userDto);
    }

    /**
     * It fetches user by id and returns a fetched user detail,
     * if user is not present, it will throw error message (User not found)
     *
     * @param id The id of the user to be fetched.
     * @return user detail as response.
     */
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    /**
     * It returns a list of all users.
     * if user table is empty, it will show empty list of user.
     *
     * @return list of user details.
     */
    @GetMapping()
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    /**
     * It takes a UserDTO object as a parameter, calls the updateUser
     * function in the userService class, and returns
     * updated object else throws error message.
     *
     * @param userDto The user object need to be updated.
     * @return updated user object as response.
     */
    @PutMapping()
    public UserDTO updateUser(@RequestBody UserDTO userDto) {
        return userService.updateUser(userDto);
    }

    /**
     * It deletes user from the database and
     * returns boolean true if deletes else throws error message(id not found).
     * <p>
     * Note: user's deleted status update into true.
     *
     * @param id The id of the user to be deleted.
     * @return boolean as response.
     */
    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }
}