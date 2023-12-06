package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;
/**
 * The {@code CampStudentMainEnquiriesMenu} class represents a view for displaying main menu options
 * related to enquiries for a student in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.student} package.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStudentMainEnquiriesMenu implements IAppView {
	/**
     * Displays main menu options related to enquiries for a student with navigation choices.
     */
    @Override
    public void display() {
        System.out.println("*********** Enquiries ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. View my enquiries");
        System.out.println("2. View others' enquiries");
        System.out.println("3. Back");
    }
}
