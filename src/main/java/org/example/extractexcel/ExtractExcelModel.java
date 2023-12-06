package org.example.extractexcel;

import org.example.users.enums.UserType;

/**
 * The ExtractExcelModel class represents Excel data in a structured format.
 *
 * @author Group1
 * @version 1.0
 */
public class ExtractExcelModel implements IExtractExcel {

    private String name;
    private String email;
    private String faculty;
    private UserType userType;
    private String userID;

    /**
     * Constructs an ExtractExcelModel object.
     *
     * @param name     The name extracted from Excel.
     * @param email    The email extracted from Excel.
     * @param faculty  The faculty extracted from Excel.
     * @param userType The user type extracted from Excel.
     * @param userID   The user ID extracted from Excel.
     */
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
