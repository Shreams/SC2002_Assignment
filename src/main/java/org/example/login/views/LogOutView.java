package org.example.login.views;

import org.example.login.interfaces.ILoginDisplay;
/**
 * The {@code LogOutView} class represents a view for displaying a logout message.
 * It implements the {@code ILoginDisplay} interface to provide the display functionality.
 * This class is part of the {@code org.example.login.views} package.
 *
 * @author Group1
 * @version 1.0
 */
public class LogOutView implements ILoginDisplay {
	 /**
     * Displays a logout message on the console.
     */
    @Override
    public void display() {
        System.out.println("##########################################");
        System.out.println("##  ~Logout, Thank you for using CAMS ~ ##");
        System.out.println("##########################################");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
