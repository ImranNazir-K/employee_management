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
 * This class represents user DTO and consist private
 * variable's like id, name, gender, contact, email, role.
 *
 * @author Govindaraj
 * @version 1.0
 */
public class UserDTO {

    private int id;
    @NotNull(message = "Null value not allowed for name")
    @Pattern(regexp = UserConstant.NAME_REGEX, message = UserConstant
            .NAME_ERROR)
    private String name;

    @NotNull(message = "Null value not allowed for gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull(message = "Null value not allowed for contact ")
    @Pattern(regexp = UserConstant.CONTACT_REGEX, message = UserConstant
            .CONTACT_ERROR)
    private String phoneNumber;
    @NotNull(message = "Null value not allowed for email")
    @Pattern(regexp = UserConstant.EMAIL_REGEX, message = UserConstant
            .EMAIL_ERROR)
    private String email;
    @NotNull(message = "Null value not allowed for role")
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