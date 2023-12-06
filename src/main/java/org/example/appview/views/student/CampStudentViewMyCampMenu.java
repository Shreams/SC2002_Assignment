package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;
/**
 * The {@code CampStudentViewMyCampMenu} class represents a view for displaying options
 * for a specific camp that a student has joined in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.student} package.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStudentViewMyCampMenu implements IAppView {
	/**
     * Displays options for a specific camp that a student has joined with 
     * navigation choices.
     */
    @Override
    public void display() {
        System.out.println("\n******** Camps Details ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Enquire about Camp");
        System.out.println("2. Suggest Camp improvements");
        System.out.println("3. Reports");
        System.out.println("4. Withdraw from Camp");
        System.out.println("5. Back");

    }
}
