package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;
/**
 * The {@code CampStudentMainCampMenu} class represents a view for displaying main menu options
 * for a student in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.student} package.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStudentMainCampMenu implements IAppView {
	 /**
     * Displays main menu options for a student with navigation choices.
     */
    @Override
    public void display() {
        System.out.println("\n*********** Camps ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. View All Camps");
        System.out.println("2. View My Camps");
        System.out.println("3. View enquiries");
        System.out.println("4. View suggestions");
        System.out.println("5. Back");
    }
}
