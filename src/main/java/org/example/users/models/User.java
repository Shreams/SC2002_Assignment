package org.example.users.models;

import org.example.users.enums.UserType;

public  class User {
    private String userID;

    private String name;
    private String password = "password";
    private String facultyName;
    private UserType userType;

    private String email;

    public User(String name,String userID, String facultyName, String email,UserType userType) {
        this.name = name;
        this.userID = userID;
        this.facultyName = facultyName;
        this.userType = userType;
    }

    public void changePassword(String newPassword){
        this.password = newPassword;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public UserType getUserType() {
        return userType;
    }



}
