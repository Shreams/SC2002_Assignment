package org.example.users.controllers;

import org.example.users.interfaces.IUser;
import org.example.users.models.StaffModel;
import org.example.users.models.User;

import java.util.ArrayList;
import java.util.Objects;
/**
 * The {@code StaffController} class is responsible for managing staff user data and interactions.
 * It implements the {@code IUser} interface to provide user-related functionality.
 * This class is part of the {@code org.example.users.controllers} package.
 *
 * @author Group1
 * @version 1.0
 */
public class StaffController implements IUser {
	/**
	 * All the staff model is stored.
	 */
    private ArrayList<User> model;
    /**
     * Constructs a new {@code StaffController} with all student model.
     *
     * @param model The list of user models.
     */
    public StaffController(ArrayList<User> model) {
        this.model = model;
        
    }


    // ########################
    // #  Accessor & Mutator  #
    // ########################
    /**
     * Removes a camp from the list of camps created by the staff with the specified user ID.
     *
     * @param userID   The user ID for which to remove the camp.
     * @param campName The name of the camp to remove.
     */
    @Override
    public void removeCampFromList(String userID, String campName) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                ((StaffModel) user).removeCampCreatedName(campName);
            }
        }
    }
    /**
     * Sets the password for the user with the specified user ID.
     *
     * @param userID   The user ID for which to set the password.
     * @param password The new password to set.
     */
    @Override
    public void setPassword(String userID,String password) {

        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                user.changePassword(password);
            }
        }
    }
    /**
     * Adds a camp to the list of camps created by the staff with the specified user ID.
     *
     * @param userID   The user ID for which to add the camp.
     * @param campName The name of the camp to add.
     */
    @Override
    public void addCampToList(String userID, String campName) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                ((StaffModel) user).addCampCreatedName(campName);
            }
        }
    }
    /**
     * Gets the list of camps created by the staff with the specified user ID.
     *
     * @param userID The user ID for which to retrieve the camp list.
     * @return An ArrayList containing the list of created camps.
     */
    @Override
    public ArrayList<String> getCampList(String userID) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                return ((StaffModel) user).getCampCreatedNames();
            }
        }
        return new ArrayList<>();
    }
    /**
     * Gets the actual name of the user with the specified user ID.
     *
     * @param userID The user ID for which to get the actual name.
     * @return The actual name of the user.
     */
    public String getUserActualName(String userID) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                return user.getName();
            }
        }
        return "";
    }
    /**
     * Retrieves the faculty name associated with the specified user ID.
     *
     * @param userID The user ID for which to retrieve the faculty name.
     * @return The faculty name.
     */
    @Override
    public String getUserFacultyName(String userID) {

        if (!model.isEmpty()) {
            for (User user : model) {
                if (user.getUserID().equals(userID)) {
                    return user.getFacultyName();
                }
            }
        }
        return "";
    }
    /**
     * Retrieves user information based on the provided user ID.
     *
     * @param userID The user ID for which to retrieve information.
     * @return An array containing userID and password.
     */
    @Override
    public String[] retrieveUser(String userID) {

        if(!model.isEmpty()) {
            for (User user : model) {
                if (user.getUserID().equals(userID)) {
                    return new String[]{user.getUserID(), user.getPassword()};
                }
            }
        }

        // Return empty string if user is not found
        return new String[0];
    }
}
