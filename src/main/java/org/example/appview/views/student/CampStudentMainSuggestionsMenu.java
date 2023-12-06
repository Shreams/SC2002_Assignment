package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;
/**
 * The {@code CampStudentMainSuggestionsMenu} class represents a view for displaying main menu options
 * related to suggestions for a student in the application.
 * It implements the {@code IAppView} interface to provide the display functionality.
 * This class is part of the {@code org.example.appview.views.student} package.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStudentMainSuggestionsMenu implements IAppView {
	/**
     * Displays main menu options related to suggestions for a student with navigation choices.
     */
    @Override
    public void display() {
        System.out.println("*********** Suggestion ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. View my suggestion");
        System.out.println("2. Back");
    }
}
