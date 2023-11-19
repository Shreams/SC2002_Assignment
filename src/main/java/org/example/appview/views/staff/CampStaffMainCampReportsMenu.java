package org.example.appview.views.staff;

import org.example.appview.interfaces.IAppView;

public class CampStaffMainCampReportsMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("\n*********** Reports ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Generate Report of Students");
        System.out.println("2. Generate Performance Report");
        System.out.println("3. Back");

    }
}
