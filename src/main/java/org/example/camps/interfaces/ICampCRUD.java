package org.example.camps.interfaces;

/**
 * This interface provides methods for creating, deleting, and editing camps.
 *
 * @author Group1
 * @version 1.0
 */
public interface ICampCRUD {

    /**
     * Creates a camp.
     *
     * @param userID      The user ID initiating the creation of the camp.
     * @param facultyName The name of the faculty associated with the camp.
     * @return The ID of the created camp.
     */
    String createCamp(String userID, String facultyName);

    /**
     * Deletes a camp.
     *
     * @param userID The user ID attempting to delete the camp.
     * @param campID The ID of the camp to be deleted.
     */
    void deleteCamp(String userID, String campID);

    /**
     * Edits a camp.
     *
     * @param userID      The user ID initiating the edit of the camp.
     * @param campID      The ID of the camp to be edited.
     * @param facultyName The updated name of the faculty associated with the camp.
     */
    void editCamp(String userID, String campID, String facultyName);
}
