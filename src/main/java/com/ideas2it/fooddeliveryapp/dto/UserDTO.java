/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Set;

import com.ideas2it.fooddeliveryapp.constant.UserConstant;
import com.ideas2it.fooddeliveryapp.util.Gender;
import com.ideas2it.fooddeliveryapp.util.Role;

/**
 * Data Transfer Object class for User that consists of
 * fields like id, name, gender, contact, email, role.
 * with getters and setters.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
public class UserDTO {

    private int id;

    @NotNull(message = UserConstant.NAME_NOT_NULL)
    @Pattern(regexp = UserConstant.NAME_REGEX, message = UserConstant
            .INVALID_NAME)
    private String name;

    @NotNull(message = UserConstant.GENDER_NOT_NULL)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = UserConstant.CONTACT_NOT_NULL)
    @Pattern(regexp = UserConstant.CONTACT_REGEX, message = UserConstant
            .INVALID_CONTACT)
    private String phoneNumber;

    @NotNull(message = UserConstant.EMAIL_NOT_NULL)
    @Pattern(regexp = UserConstant.EMAIL_REGEX, message = UserConstant
            .INVALID_EMAIL)
    private String email;

    @NotNull(message = UserConstant.USER_NAME_NOT_NULL)
    @Pattern(regexp = UserConstant.USER_NAME_REGEX, message = UserConstant
            .INVALID_USER_NAME)
    private String userName;

    @NotNull(message = UserConstant.PASSWORD_NOT_NULL)
    @Pattern(regexp = UserConstant.PASSWORD_REGEX, message = UserConstant
            .INVALID_PASSWORD)
    private String password;

    @NotNull(message = UserConstant.ROLE_NOT_NULL)
    @Enumerated(EnumType.STRING)
    private Role role;

    private Set<AddressDTO> addressList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<AddressDTO> getAddressList() {
        return addressList;
    }

    public void setAddressList(Set<AddressDTO> addressList) {
        this.addressList = addressList;
    }
}