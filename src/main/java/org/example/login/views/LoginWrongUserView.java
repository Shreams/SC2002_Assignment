package org.example.login.views;

import org.example.login.interfaces.ILoginDisplay;

public class LoginWrongUserView implements ILoginDisplay {
    @Override
    public void display() {
        // Either wrong username or password
        System.out.println("Wrong username or password");
    }
}
