package org.example.users.models;

import org.example.users.enums.UserType;
/**
 * The {@code User} class represents a user in the system.
 * It includes information such as the user ID, name, password, faculty name, email, and user type.
 * This class is part of the {@code org.example.users.models} package.
 *
 * @author Group1
 * @version 1.0
 */
public class User {
	/**
	 * The userID of a user.
	 */
    private String userID;
    /**
     * The actual name of a user.
     */
    private String name;
    /**
     * The password to login for a user.
     */
    private String password = "password";
    /**
     * The name of a faculty the user is in.
     */
    private String facultyName;
    /**
     * The type of user (staff or student).
     */
    private UserType userType;
    /**
     * The emial of a user.
     */
    private String email;
    /**
     * Constructs a new user with the specified name, user ID, faculty name, email, and user type.
     *
     * @param name        The name of the user.
     * @param userID      The user ID.
     * @param facultyName The faculty name of the user.
     * @param email       The email of the user.
     * @param userType    The user type (e.g., student, faculty).
     */
    public User(String name,String userID, String facultyName, String email,UserType userType) {
        this.name = name;
        this.userID = userID;
        this.facultyName = facultyName;
        this.userType = userType;
        this.email = email;
    }
    /**
     * Changes the password of the user.
     *
     * @param newPassword The new password to set.
     */
    public void changePassword(String newPassword){
        this.password = newPassword;
    }
    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public String getUserID() {
        return userID;
    }
    /**
     * Gets the password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the faculty name of the user.
     *
     * @return The faculty name of the user.
     */
    public String getFacultyName() {
        return facultyName;
    }
    /**
     * Gets the user type.
     *
     * @return The user type.
     */
    public UserType getUserType() {
        return userType;
    }
    /**
     * Gets the email of a user.
     *
     * @return The email of a user.
     */
    public String getEmail() {
    	return email;
    }



}
