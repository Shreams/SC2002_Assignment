package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;

public class CampStudentViewMyCampMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("\n******** Camps Details ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Enquire about Camp");
        System.out.println("2. Suggest Camp improvements");
        System.out.println("3. Reports");
        System.out.println("4. Withdraw from Camp");
        System.out.println("5. Back");

    }
}
