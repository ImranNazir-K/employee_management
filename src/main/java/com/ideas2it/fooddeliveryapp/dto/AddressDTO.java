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
 * Data Transfer Object class for Address that consists of
 * fields like id, door_no, street_name, city, area, pin_code
 * with getters and setters.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
public class AddressDTO {

    private int id;

    @NotNull(message = UserConstant.DOOR_NOT_NULL)
    @Pattern(regexp = UserConstant.DOOR_NO_REGEX, message = UserConstant
            .INVALID_DOOR_NO)
    private String doorNo;

    @NotNull(message = UserConstant.STREET_NOT_NULL)
    @Pattern(regexp = UserConstant.TEXT_REGEX, message = UserConstant
            .INVALID_STREET_NAME)
    private String streetName;

    @NotNull(message = UserConstant.AREA_NOT_NULL)
    @Pattern(regexp = UserConstant.TEXT_REGEX, message = UserConstant
            .INVALID_AREA)
    private String area;

    @NotNull(message = UserConstant.CITY_NOT_NULL)
    @Pattern(regexp = UserConstant.TEXT_REGEX, message = UserConstant
            .INVALID_CITY)
    private String city;

    @NotNull(message = UserConstant.PIN_CODE_NOT_NULL)
    @Pattern(regexp = UserConstant.PIN_CODE_REGEX, message = UserConstant
            .INVALID_PIN_CODE)
    private String pinCode;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}