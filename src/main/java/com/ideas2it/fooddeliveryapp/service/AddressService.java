package com.ideas2it.fooddeliveryapp.service;

import com.ideas2it.fooddeliveryapp.dto.AddressDTO;

import java.util.List;
public interface AddressService {

    /**
     * It takes an addressDTO as input and saves the
     * address object to the database
     *
     * @param addressDto This is the object that is passed from the controller.
     * @return address object if successfully inserted in database.
     */
    AddressDTO createAddress(AddressDTO addressDto);


    /**
     * Get a details of individual address from databases
     * if address aren't exist, it will pass user not found message.
     *
     * @param id gives a addressId to get particular address details
     * @return addressDTO gives a response as a address DTO details
     */
    AddressDTO getAddressById(int id);

    /**
     * Updates the user details in the database.
     *
     * @param addressDto send a updated user details to update on database
     * @return addressDTO gives a response as a address DTO details
     */
    AddressDTO updateAddress(AddressDTO addressDto);

    /**
     * It deletes the address of the user if the address
     * is present in the user's address list
     *
     * @param id This is reference for address object.
     * @return A string - deleted message as response statement
     */
    boolean deleteAddressById(int id);
}