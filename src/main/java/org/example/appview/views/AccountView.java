package org.example.appview.views;

import org.example.appview.interfaces.IAppView;

/**
 * Represents the account view of the application.
 *
 * @author Group1
 * @version 1.0
 */
public class AccountView implements IAppView {

    /**
     * Displays the account view options.
     */
    @Override
    public void display() {
        System.out.println("\n*********** Accounts ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Change Password");
        System.out.println("2. Back");
    }
}
