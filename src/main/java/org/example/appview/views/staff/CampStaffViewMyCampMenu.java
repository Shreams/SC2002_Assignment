package org.example.appview.views.staff;

import org.example.appview.interfaces.IAppView;


public class CampStaffViewMyCampMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("\n******** Camps Details ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Edit Camp");
        System.out.println("2. View Suggestions");
        System.out.println("3. View Enquiries");
        System.out.println("4. Delete Camp");
        System.out.println("5. Back");

    }
}
