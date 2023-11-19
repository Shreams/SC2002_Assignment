package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;

public class CampStudentViewOtherQueriesMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Reply to enquiry");
        System.out.println("2. Back");
    }
}
