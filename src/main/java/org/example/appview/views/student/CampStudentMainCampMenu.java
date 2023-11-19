package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;

public class CampStudentMainCampMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("\n*********** Camps ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. View All Camps");
        System.out.println("2. View My Camps");
        System.out.println("3. View enquiries");
        System.out.println("4. View suggestions");
        System.out.println("5. Back");
    }
}
