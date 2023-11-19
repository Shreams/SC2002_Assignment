package org.example.appview.views.staff;

import org.example.appview.interfaces.IAppView;

public class CampStaffMainCampMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("\n*********** Camps ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Create Camp");
        System.out.println("2. View All Camps");
        System.out.println("3. View My Camps"); //-> Ask them to select a camp to view then inside will ask to 1. edit such as visibility 2. View Suggestions 3. View Enquiries 4. Delete Camp
        System.out.println("4. Report"); //--> 1. GenerateReportOfStud 2. GeneratePerformanceReport
        System.out.println("5. Back");


    }
}
