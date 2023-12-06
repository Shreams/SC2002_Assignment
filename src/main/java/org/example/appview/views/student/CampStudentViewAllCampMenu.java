package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;
/**
 * The {@code CampStudentViewAllCampMenu} class represents a view for displaying options
 * for a student to choose when they are not part of the camp in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.student} package.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStudentViewAllCampMenu implements IAppView {
	 /**
     * Displays options for a student with navigation choices if they are not part a camp.
     */
    @Override
    public void display() {
        System.out.println("\n*********** Camps Details ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Join Camp");
        System.out.println("2. Enquire about Camp");
        System.out.println("3. Suggest Camp improvements");
        System.out.println("4. Back");
    }
}
