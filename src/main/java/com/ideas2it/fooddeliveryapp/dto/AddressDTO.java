/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.ideas2it.fooddeliveryapp.constant.UserConstant;

/**
 * This class represents address DTO and consists private
 * variable's id, door_no, street_name, city, landmark, pin_code.
 *
 * @author Govindaraj
 * @version 1.0
 */
public class AddressDTO {
    private int id;
    @NotNull(message = "Null value not allowed for door_no")
    @Pattern(regexp = UserConstant.DOOR_NO_REGEX,message = UserConstant
            .DOOR_NO_ERROR)
    private String doorNo;
    @NotNull(message = "Null value not allowed for street name")
    @Pattern(regexp = UserConstant.NAME_REGEX,message = UserConstant
            .ADDRESS_ERROR)
    private String streetName;
    @NotNull(message = "Null value not allowed for city")
    @Pattern(regexp = UserConstant.NAME_REGEX,message = UserConstant
            .ADDRESS_ERROR)
    private String city;

    @NotNull(message = "Null value not allowed for landmark")
    @Pattern(regexp = UserConstant.NAME_REGEX,message = UserConstant
            .ADDRESS_ERROR)
    private String landMark;

    @NotNull(message = "Null value not allowed for pin code")
    @Pattern(regexp = UserConstant.PIN_CODE_REGEX, message = UserConstant
            .PIN_CODE_ERROR)
    private int pinCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}