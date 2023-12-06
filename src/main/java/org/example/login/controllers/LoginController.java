package org.example.login.controllers;

import org.example.login.models.LoginModel;
import org.example.login.views.LogOutView;
import org.example.users.enums.UserType;
import org.example.login.interfaces.ILoginDisplay;
import org.example.login.views.LoginView;
import org.example.login.views.LoginWrongUserView;
import org.example.users.interfaces.IUser;
import java.util.Date;
import java.util.Scanner;
/**
 * The {@code LoginController} class handles user authentication and related operations.
 * It provides methods for logging in, accessing and modifying user information, and more.
 * This class is part of the {@code org.example.login.controllers} package.
 *
 * @author Group1
 * @version 1.0
 */
public class LoginController {

    //Authentication layer
	/**
	 * The LoginModel for the user that is currently login.
	 */
    private LoginModel userObj = null;
    /**
     * The scanner used for user input.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * Constructs a new LoginController.
     */
    public LoginController() {}


    // ######################
    // #  Authentication    #
    // ######################
    /**
     * Authenticates the user with the provided credentials.
     *
     * @param user     The user interface for retrieving user information.
     * @param userType The type of the user (staff or student).
     * @throws Exception If authentication fails.
     */
    public void login(IUser user, UserType userType) throws Exception {

        System.out.print("Enter userID: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        String[] userCredentials = user.retrieveUser(username);

        if (new Authentication().authenticate(userCredentials, username, password)) {
            userObj = new LoginModel(username, password, userType, user.getUserFacultyName(username));
            System.out.println("Login successful");
        }else {
            throw new Exception("Wrong username or password");
        }
    }

    // ########################
    // #  Accessor & Mutator  #
    // ########################
    /**
     * Gets the user ID associated with the logged-in user.
     *
     * @return The user ID.
     * @throws Exception If the user object is null.
     */
    public String getUserID() throws Exception{
        if(userObj == null){
            throw new Exception("User object cannot be null");
        } else {
            return userObj.getUserID();
        }
    }
    /**
     * Gets the faculty name associated with the logged-in user.
     *
     * @return The faculty name.
     * @throws Exception If the user object is null.
     */
    public String getFacultyName() throws Exception{
        if(userObj == null){
            throw new Exception("User object cannot be null");
        } else {
            return userObj.getFacultyName();
        }
    }
    /**
     * Gets the login date associated with the logged-in user.
     *
     * @return The login date.
     * @throws Exception If the user object is null.
     */
    public Date getLoginDate() throws Exception{
        if(userObj == null){
            throw new Exception("User object cannot be null");
        } else {
            return userObj.getLoginDate();
        }
    }
    /**
     * Sets a new password for the logged-in user.
     *
     * @param user The user interface for updating user password.
     * @throws Exception If the user object is null.
     */
    public void setPassword(IUser user) throws Exception{
        if(userObj == null){
            throw new Exception("User object cannot be null");
        } else {
            System.out.println("Please change your password");
            System.out.print("Enter new password: ");
            String newPassword = scanner.next();

            // Update loginModel
            userObj.changePassword(newPassword);

            //Update database
            user.setPassword(userObj.getUserID(), newPassword);
        }
    }

    // ################
    // #  Validators  #
    // ################
    /**
     * Checks if the user is using the default password.
     *
     * @return True if the user is using the default password, false otherwise.
     * @throws IllegalArgumentException If the user object is null.
     */
    public boolean isUsingDefaultPassword() throws IllegalArgumentException{
        return new Authentication().isUsingDefaultPassword(userObj);
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        userObj = null;
        (new LogOutView()).display();
    }
    /**
     * Renders the specified login display view.
     *
     * @param view The login display view to render.
     */
    public void render(ILoginDisplay view){
        view.display();
    }

}
