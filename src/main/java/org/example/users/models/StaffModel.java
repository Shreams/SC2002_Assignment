package org.example.users.models;

import org.example.users.enums.UserType;

import java.util.ArrayList;
/**
 * The {@code StaffModel} class represents a staff user in the system.
 * It extends the {@code User} class and includes additional information such as a list of created camps.
 * This class is part of the {@code org.example.users.models} package.
 *
 * @author Group1
 * @version 1.0
 */
public class StaffModel extends User {
	/**
	 * The lists of all camp created by the staff.
	 */
    private ArrayList<String> campCreatedNames;
    /**
     * Constructs a new staff model with the specified name, user ID, faculty name, email, and user type.
     *
     * @param name        The name of the staff member.
     * @param userID      The user ID.
     * @param facultyName The faculty name of the staff member.
     * @param email       The email of the staff member.
     * @param userType    The user type (staff).
     */
    public StaffModel(String name, String userID, String facultyName, String email, UserType userType) {
        super(name, userID, facultyName, email, userType);
        this.campCreatedNames = new ArrayList<String>();
    }
    /**
     * Gets the list of camp names created by the staff member.
     *
     * @return An ArrayList containing the names of created camps.
     */
    public ArrayList<String> getCampCreatedNames() {
        return campCreatedNames;
    }
    /**
     * Sets the list of camp names created by the staff member.
     *
     * @param campCreatedNames The new list of camp names.
     */
    public void setCampCreatedNames(ArrayList<String> campCreatedNames) {
        this.campCreatedNames = campCreatedNames;
    }
    /**
     * Adds a camp name to the list of created camps by the staff member.
     *
     * @param campName The name of the camp to add.
     */
    public void addCampCreatedName(String campName){
        this.campCreatedNames.add(campName);
    }
    /**
     * Removes a camp name from the list of created camps by the staff member.
     *
     * @param campName The name of the camp to remove.
     */
    public void removeCampCreatedName(String campName){
        this.campCreatedNames.remove(campName);
    }
}
