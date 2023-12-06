package org.example.extractexcel;

/**
 * The ExtractExcelView class handles printing Excel details.
 *
 * @author Group1
 * @version 1.0
 */
public class ExtractExcelView {

    /**
     * Prints details extracted from Excel.
     *
     * @param name    The name extracted from Excel.
     * @param email   The email extracted from Excel.
     * @param faculty The faculty extracted from Excel.
     * @param userID  The user ID extracted from Excel.
     */
    public void printExcelDetails(String name, String email, String faculty, String userID) {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Faculty: " + faculty);
        System.out.println("User ID: " + userID);
        System.out.println();
    }
}