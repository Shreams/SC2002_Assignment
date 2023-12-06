package org.example.login.controllers;

import org.example.login.models.LoginModel;
/**
 * The {@code Authentication} class provides methods for user authentication and password validation.
 * It includes functionality for authenticating user credentials and checking if a user is using the default password.
 * This class is part of the {@code org.example.login.controllers} package.
 *
 * @author Your Name
 * @version 1.0
 */
public class Authentication {

    /**
     * Authenticates the user based on the provided user credentials, username, and password.
     *
     * @param userCredentials An array containing userID and user password
     * @param username        The username for authentication.
     * @param password        The password for authentication.
     * @return {@code true} if authentication is successful, {@code false} otherwise.
     * @throws IllegalArgumentException If the user credentials array is empty.
     */
    public boolean authenticate(String[] userCredentials,String username, String password) {

        if(userCredentials.length ==0){
            throw new IllegalArgumentException("Wrong username or password");
        }

        return userCredentials[0].equals(username) && userCredentials[1].equals(password);
    }

    /**
     * Checks if the user is using the default password.
     *
     * @param userObj The {@code LoginModel} object representing the user.
     * @return {@code true} if the user is using the default password, {@code false} otherwise.
     * @throws IllegalArgumentException If the user object is null.
     */
    public boolean isUsingDefaultPassword(LoginModel userObj) throws  IllegalArgumentException {
        if (userObj == null) {
            throw new IllegalArgumentException("User object cannot be null");
        } else {
            return userObj.getPassword().equals("password");
        }

    }

}
