package org.example.camps.interfaces;

import java.util.ArrayList;

/**
 * This interface provides methods for controlling and viewing camp-related information.
 *
 * @author Group1
 * @version 1.0
 */
public interface ICampControllerView {

    /**
     * Shows details of a specific camp.
     *
     * @param campName The name of the camp to display details for.
     */
    void showCampDetails(String campName);

    /**
     * Views camps by applying specified filters.
     *
     * @param filterChoice The choice of filter to be applied.
     * @param userID       The user ID.
     * @param facultyName  The name of the faculty.
     * @param viewAll      Indicates whether to view all camps or specific ones.
     * @return An ArrayList of camp names based on the applied filters.
     */
    ArrayList<String> viewCampsByFilter(int filterChoice, String userID, String facultyName, boolean viewAll);

}
