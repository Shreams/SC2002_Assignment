package org.example.appview.views.staff;

import org.example.appview.interfaces.IAppView;
/**
 * The {@code CampStaffViewMyCampMenu} class represents a view for displaying menu options
 * related to a camp managed by a staff member in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.staff} package.
 *
 * @author Group1
 * @version 1.0
 */

public class CampStaffViewMyCampMenu implements IAppView {
	/**
     * Displays menu options related to a camp managed by a staff member with navigation choices.
     */
    @Override
    public void display() {
        System.out.println("\n******** Camps Details ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Edit Camp");
        System.out.println("2. View Suggestions");
        System.out.println("3. View Enquiries");
        System.out.println("4. Delete Camp");
        System.out.println("5. Back");

    }
}
