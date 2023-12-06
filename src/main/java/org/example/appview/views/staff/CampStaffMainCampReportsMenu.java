package org.example.appview.views.staff;

import org.example.appview.interfaces.IAppView;
/**
 * The {@code CampStaffMainCampReportsMenu} class represents a view for displaying menu options
 * related to generating reports for a camp managed by a staff member in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.staff} package.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStaffMainCampReportsMenu implements IAppView {
	 /**
     * Displays menu options related to generating reports for a camp managed by a staff member with navigation choices.
     */
    @Override
    public void display() {
        System.out.println("\n*********** Reports ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Generate Report of Students");
        System.out.println("2. Generate Performance Report");
        System.out.println("3. Back");

    }
}
