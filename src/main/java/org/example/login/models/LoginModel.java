package org.example.login.models;

import org.example.users.enums.UserType;

import java.util.Date;

public class LoginModel {

    private String userID;
    private String password;
    private UserType userType;

    private Date loginDate;

    private String facultyName;

    public LoginModel(String userID, String password, UserType userType, String facultyName) {
        this.userID = userID;
        this.password = password;
        this.userType = userType;
        this.loginDate = new Date();
        this.facultyName = facultyName;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public Date getLoginDate() {
        return loginDate;
    }
}
