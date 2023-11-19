package org.example.appview.views;

import org.example.appview.interfaces.IAppView;

public class AccountView implements IAppView {

    @Override
    public void display() {
        System.out.println("\n*********** Accounts ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Change Password");
        System.out.println("2. Back");
    }
}
