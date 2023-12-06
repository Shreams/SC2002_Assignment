package org.example.login.views;

import org.example.login.interfaces.ILoginDisplay;
/**
 * The {@code LoginWrongUserView} class represents a view for displaying a message when login credentials are incorrect.
 * It implements the {@code ILoginDisplay} interface to provide the display functionality.
 * This class is part of the {@code org.example.login.views} package.
 *
 * @author Group1
 * @version 1.0
 */
public class LoginWrongUserView implements ILoginDisplay {
	 /**
     * Displays a message indicating that the login credentials (username or password) are incorrect.
     */
    @Override
    public void display() {
        // Either wrong username or password
        System.out.println("Wrong username or password");
    }
}
