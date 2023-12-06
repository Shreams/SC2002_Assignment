package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;

/**
 * Represents the view for handling other queries in the camp for students.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStudentViewOtherQueriesMenu implements IAppView {

    /**
     * Displays the menu options for handling other queries.
     */
    @Override
    public void display() {
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Reply to enquiry");
        System.out.println("2. Back");
    }
}
