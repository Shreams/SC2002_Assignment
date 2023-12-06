package org.example.users.interfaces;

import java.util.ArrayList;
/**
 * The {@code IUser} interface defines the contract for user-related operations.
 * Implementing classes should provide functionality for retrieving user information,
 * setting passwords, managing camps, and accessing user-specific details.
 * This interface is part of the {@code org.example.users.interfaces} package.
 *
 * @author Group1
 * @version 1.0
 */
public interface IUser {
	/**
     * Retrieves user information based on the provided user ID.
     *
     * @param userID The user ID for which to retrieve information.
     * @return An array containing user information.
     */
    public String[] retrieveUser(String userID);
    /**
     * Sets the password for the specified user ID.
     *
     * @param userID   The user ID for which to set the password.
     * @param password The new password to set.
     */
    public void setPassword(String userID, String password);
    /**
     * Retrieves the faculty name associated with the specified user ID.
     *
     * @param userID The user ID for which to retrieve the faculty name.
     * @return The user faculty name.
     */
    public String getUserFacultyName(String userID);
    /**
     * Adds a camp to the list of camps associated with the specified user ID.
     *
     * @param userID   The user ID for which to add the camp.
     * @param campName The name of the camp to add.
     */
    /**
     * Adds a camp to the list of camps associated with the specified user ID.
     *
     * @param userID   The user ID to be used.
     * 				   To include the camp into the user's model.
     * @param campName The name of the camp to add.
     */
    public void addCampToList(String userID, String campName);
    /**
     * Removes a camp from the list of camps associated with the specified user ID.
     *
     * @param userID   The user ID to be used.
     * 				   To remove the camp in the user's model.
     * @param campName The name of the camp to remove.
     */
    public void removeCampFromList(String userID, String campName);
    /**
     * Gets the list of camps associated with the specified user ID.
     *
     * @param userID   The user ID for which to retrieve the camp list.
     * @return An ArrayList containing the list of camps.
     */
    public ArrayList<String> getCampList(String userID);
}
