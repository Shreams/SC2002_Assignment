package org.example.login.views;

import org.example.login.interfaces.ILoginDisplay;

public class LogOutView implements ILoginDisplay {
    @Override
    public void display() {
        System.out.println("##########################################");
        System.out.println("##  ~Logout, Thank you for using CAMS ~ ##");
        System.out.println("##########################################");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
