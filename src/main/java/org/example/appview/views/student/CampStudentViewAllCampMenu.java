package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;

public class CampStudentViewAllCampMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("\n*********** Camps Details ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Join Camp");
        System.out.println("2. Enquire about Camp");
        System.out.println("3. Suggest Camp improvements");
        System.out.println("4. Back");
    }
}
