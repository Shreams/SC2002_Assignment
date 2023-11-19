package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;

public class CampStudentMainEnquiriesMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("*********** Enquiries ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. View my enquiries");
        System.out.println("2. View others' enquiries");
        System.out.println("3. Back");
    }
}
