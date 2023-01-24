package com.ideas2it.fooddeliveryapp.service;

import com.ideas2it.fooddeliveryapp.dto.AddressDTO;

/**
 * Interface for AddressServiceImpl class to perform CRUD
 * operations for Address.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
public interface AddressService {

    /**
     * Creates address.
     *
     * @param addressDto The address object.
     * @return AddressDTO object which was created.
     */
    AddressDTO createAddress(AddressDTO addressDto);

    /**
     * Get address by its id if address doesn't exist, it will
     * throw NotFoundException.
     *
     * @param id the id of the address.
     * @return a AddressDTO object.
     */
    AddressDTO getAddressById(int id);

    /**
     * Updates a address.
     *
     * @param addressDto AddressDTO object.
     * @return AddressDTO which was updated.
     */
    AddressDTO updateAddress(AddressDTO addressDto);

    /**
     * Deletes the address by its id.
     *
     * @param id The id of the address to be deleted.
     * @return true if address deleted.
     */
    boolean deleteAddressById(int id);
}