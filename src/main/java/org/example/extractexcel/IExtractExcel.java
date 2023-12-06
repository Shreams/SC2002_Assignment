package org.example.extractexcel;

import org.example.users.enums.UserType;

/**
 * The IExtractExcel interface shows the methods to extract Excel data.
 * The ExtractExcelModel class implements this interface.
 *
 * @author Group1
 * @version 1.0
 */
public interface IExtractExcel {

    /**
     * Gets the name from Excel data.
     *
     * @return The name extracted from Excel.
     */
    String getName();

    /**
     * Gets the email from Excel data.
     *
     * @return The email extracted from Excel.
     */
    String getEmail();

    /**
     * Gets the faculty name from Excel data.
     *
     * @return The faculty name extracted from Excel.
     */
    String getFacultyName();

    /**
     * Gets the user type from Excel data.
     *
     * @return The user type extracted from Excel.
     */
    UserType getUserType();

    /**
     * Gets the user ID from Excel data.
     *
     * @return The user ID extracted from Excel.
     */
    String getUserID();
}