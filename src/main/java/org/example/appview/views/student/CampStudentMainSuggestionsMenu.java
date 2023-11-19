package org.example.appview.views.student;

import org.example.appview.interfaces.IAppView;

public class CampStudentMainSuggestionsMenu implements IAppView {
    @Override
    public void display() {
        System.out.println("*********** Suggestion ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. View my suggestion");
        System.out.println("2. Back");
    }
}
