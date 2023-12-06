package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;
/**
 * The {@code CampStudentViewMyQueriesMenu} class represents a view for displaying options
 * related to the student's own queries for a camp in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.student} package.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStudentViewMyQueriesMenu implements IAppView {
	/**
     * Displays options for the student's own queries related to a camp with navigation choices.
     */
    @Override
    public void display() {
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Edit enquiry");
        System.out.println("2. Delete enquiry");
        System.out.println("3. Back");
    }
}
