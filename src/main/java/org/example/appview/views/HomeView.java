package org.example.appview.views;

import org.example.appview.interfaces.IAppView;

public class HomeView implements IAppView{
    @Override
    public void display() {
        System.out.println("\n*********** Home ***********");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. Accounts");
        System.out.println("2. Profile");
        System.out.println("3. Camps");
        System.out.println("4. Logout");
    }
}
