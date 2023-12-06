package org.example.camps.interfaces;

import java.util.ArrayList;

/**
 * This interface provides methods for retrieving camp information.
 *
 * @author Group1
 * @version 1.0
 */
public interface ICampInfoGetters {

    /**
     * Retrieves the participants in a camp.
     *
     * @param campID The ID of the camp.
     * @return An ArrayList of participant IDs in the camp.
     */
    ArrayList<String> getParticipantsInCamp(String campID);

    /**
     * Retrieves the camp committee members in a camp.
     *
     * @param campID The ID of the camp.
     * @return An ArrayList of committee member IDs in the camp.
     */
    ArrayList<String> getCampCommitteeInCamp(String campID);

    /**
     * Retrieves the blacklist entries in a camp.
     *
     * @param campID The ID of the camp.
     * @return An ArrayList of blacklisted IDs in the camp.
     */
    ArrayList<String> getBlacklistInCamp(String campID);

    /**
     * Retrieves the names of camps associated with a user and faculty.
     *
     * @param userID      The user ID.
     * @param facultyName The name of the faculty.
     * @return An ArrayList of camp names associated with the user and faculty.
     */
    ArrayList<String> getCampNames(String userID, String facultyName);
}
