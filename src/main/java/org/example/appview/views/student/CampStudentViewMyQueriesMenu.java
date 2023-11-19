package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;

public class CampStudentViewMyQueriesMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Edit enquiry");
        System.out.println("2. Delete enquiry");
        System.out.println("3. Back");
    }
}
