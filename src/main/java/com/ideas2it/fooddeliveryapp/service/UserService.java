package com.ideas2it.fooddeliveryapp.service;

import java.util.List;

import com.ideas2it.fooddeliveryapp.dto.UserDTO;

public interface UserService {

    /**
     * Customer or Delivery person details store in the database
     * before that check the user emailId and phone number must be unique in database.
     * if emailId and phone already exist in table, it will show error message.

     * @param userDto pass user details to store the user in the databases
     * @return userDto  after store in database to give a response as user details.
     */
    UserDTO createUser(UserDTO userDto);

    /**
     * Get a details of individual user from databases
     * if user aren't exist, it will pass user not found message.
     *
     * @param id gives a userId to get particular user details
     * @return userDTO gives a response as a user DTO details
     */
    UserDTO getUserById(int id);

    /**
     * Get details of all user details in databases
     *
     * @return list of user  gives a response of all user details.
     */
    List<UserDTO> getAllUser();

    /**
     * Updates the user details in the database.
     * if user update the emailId and phone number,
     * it checks emailId and phone number shouldn't already exist.
     *
     * @param userDto send a updated user details to update on database
     * @return userDTO gives a response as a user DTO details
     */
    UserDTO updateUser(UserDTO userDto);

    /**
     * delete the  user details in the database.
     *
     * @param id gives a userId to get particular user details
     * @return userDTO gives a response as a user DTO details
     */
    boolean deleteUserById(int id);
}
