package org.example.login.views;

import org.example.login.interfaces.ILoginDisplay;

public class LoginView implements ILoginDisplay {

    @Override
    public void display(){
        System.out.println("#################################");
        System.out.println("##    ~ Welcome to CAMS ඞ ~    ##");
        System.out.println("#################################");
        System.out.println("~Select domain~");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.print("Enter domain: ");
    }
}
