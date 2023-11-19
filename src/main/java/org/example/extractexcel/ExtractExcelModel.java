package org.example.extractexcel;

import org.example.users.enums.UserType;

public class ExtractExcelModel implements IExtractExcel {

    private String name;
    private String email;
    private String faculty;
    private UserType userType;
    private String userID;

    public ExtractExcelModel(String name, String email, String faculty, UserType userType, String userID) {
        this.name = name;
        this.email = email;
        this.faculty = faculty;
        this.userType = userType;
        this.userID = userID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFacultyName() {
        return faculty;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

    @Override
    public String getUserID() {
        return userID;
    }
}
