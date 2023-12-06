package org.example.login.models;

import org.example.users.enums.UserType;

import java.util.Date;
/**
 * The {@code LoginModel} class has user login information.
 * It includes details such as user ID, password, user type, login date, and faculty name.
 * This class is part of the {@code org.example.login.models} package.
 *
 * @author Group1
 * @version 1.0
 */
public class LoginModel {
	/**
	 * The userID of a user.
	 */
    private String userID;
    /**
     * The user's password.
     */
    private String password;
    /**
     *  The type of the user (staff,student).
     */
    private UserType userType;
    /**
     * The login date by the user.
     */
    private Date loginDate;
    /**
     * The name of the user's faculty.
     */
    private String facultyName;
    /**
     * Constructs a new LoginModel with the specified user information.
     *
     * @param userID      The user ID.
     * @param password    The user password.
     * @param userType    The type of the user (staff,student).
     * @param facultyName The name of the user's faculty.
     */
    public LoginModel(String userID, String password, UserType userType, String facultyName) {
        this.userID = userID;
        this.password = password;
        this.userType = userType;
        this.loginDate = new Date();
        this.facultyName = facultyName;
    }
    /**
     * Changes the password for the user.
     *
     * @param newPassword The new password to set.
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
    /**
     * Gets the password of the user.
     *
     * @return The user password.
     */
    public String getPassword() {
        return password;
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
     * Gets the name of the user's faculty.
     *
     * @return The faculty name.
     */
    public String getFacultyName() {
        return facultyName;
    }
    /**
     * Gets the login date of the user.
     *
     * @return The login date.
     */
    public Date getLoginDate() {
        return loginDate;
    }
}
