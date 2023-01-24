/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import java.util.Set;

import com.ideas2it.fooddeliveryapp.util.Gender;
import com.ideas2it.fooddeliveryapp.util.Role;

/**
 * Response Object class for User that consists of
 * fields like id, name, gender, contact, email, role.
 * with getters and setters.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
public class UserResponseDTO {

    private int id;
    private String name;
    private Gender gender;
    private String phoneNumber;
    private String email;
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