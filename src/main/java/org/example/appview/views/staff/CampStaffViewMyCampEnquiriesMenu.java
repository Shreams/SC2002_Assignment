package org.example.appview.views.staff;

import org.example.appview.interfaces.IAppView;

public class CampStaffViewMyCampEnquiriesMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Reply enquiry");
        System.out.println("2. Back");
    }
}
