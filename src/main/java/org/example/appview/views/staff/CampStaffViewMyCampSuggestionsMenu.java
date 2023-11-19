package org.example.appview.views.staff;

import org.example.appview.interfaces.IAppView;

public class CampStaffViewMyCampSuggestionsMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Accept suggestion");
        System.out.println("2. Reject suggestion");
        System.out.println("3. Back");
    }
}
