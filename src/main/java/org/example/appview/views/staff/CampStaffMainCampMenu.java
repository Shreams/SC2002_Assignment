package org.example.appview.views.staff;

import org.example.appview.interfaces.IAppView;

/**
 * The {@code CampStaffMainCampMenu} class represents a view for displaying menu options
 * for a staff member in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.staff} package.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStaffMainCampMenu implements IAppView {
	 /**
     * Displays menu options for a staff member with navigation choices.
     */
    @Override
    public void display() {
        System.out.println("\n*********** Camps ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Create Camp");
        System.out.println("2. View All Camps");
        System.out.println("3. View My Camps"); //-> Ask them to select a camp to view then inside will ask to 1. edit such as visibility 2. View Suggestions 3. View Enquiries 4. Delete Camp
        System.out.println("4. Report"); //--> 1. GenerateReportOfStud 2. GeneratePerformanceReport
        System.out.println("5. Back");


    }
}
