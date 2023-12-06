package org.example.login.views;

import org.example.login.interfaces.ILoginDisplay;
/**
 * The {@code LoginView} class represents a view for displaying the initial login screen.
 * It implements the {@code ILoginDisplay} interface to provide the display functionality.
 * This class is part of the {@code org.example.login.views} package.
 *
 * @author Group1
 * @version 1.0
 */
public class LoginView implements ILoginDisplay {
	/**
     * Displays the initial login screen with a welcome message and domain selection options.
     */
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
