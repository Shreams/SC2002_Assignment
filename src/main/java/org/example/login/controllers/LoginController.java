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

public class LoginController {

    //Authentication layer
    private LoginModel userObj = null;

    private ILoginDisplay view;

    private Scanner scanner = new Scanner(System.in);

    private Date loginDate;

    public LoginController() {}


    // ######################
    // #  Authentication    #
    // ######################

    public void login(IUser user, UserType userType) throws Exception {

        System.out.print("Enter userID: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        String[] userCredentials = user.retrieveUser(username);

        if (authenticate(userCredentials,username, password)) {
            userObj = new LoginModel(username, password, userType, user.getUserFacultyName(username));
            System.out.println("Login successful");
        }else {
            throw new Exception("Wrong username or password");
        }
    }

    private boolean authenticate(String[] userCredentials,String username, String password) {

        if(userCredentials.length ==0){
            //showWrongUser();
            throw new IllegalArgumentException("Wrong username or password");
        }

        return userCredentials[0].equals(username) && userCredentials[1].equals(password);
    }

    // ########################
    // #  Accessor & Mutator  #
    // ########################

    public String getUserID(){
        if(userObj == null){
            throw new IllegalArgumentException("User object cannot be null");
        } else {
            return userObj.getUserID();
        }
    }

    public String getFacultyName(){
        if(userObj == null){
            throw new IllegalArgumentException("User object cannot be null");
        } else {
            return userObj.getFacultyName();
        }
    }

    public Date getLoginDate(){
        if(userObj == null){
            throw new IllegalArgumentException("User object cannot be null");
        } else {
            return userObj.getLoginDate();
        }
    }

    public void setPassword(IUser user) {
        if(userObj == null){
            throw new IllegalArgumentException("User object cannot be null");
        } else {
            System.out.println("Please change your password");
            System.out.print("Enter new password: ");
            String newPassword = scanner.next();

            // Update loginModel
            userObj.changePassword(newPassword);

            //Update database
            user.setPassword(getUserID(), newPassword);
        }
    }

    // ################
    // #  Validators  #
    // ################

    public boolean isUsingDefaultPassword(){
        if(userObj == null){
            throw new IllegalArgumentException("User object cannot be null");
        } else {
            return userObj.getPassword().equals("password");
        }
    }

//    public String[] retrieveUser(String userID) {
//        if(userObj == null){
//            throw new IllegalArgumentException("User object cannot be null");
//        } else {
//            return new String[]{userObj.getUserID(), userObj.getPassword()};
//        }
//    }

    public void logout() {

        userObj = null;
        showLogOut();

    }

    public void showLogOut() {
        view = new LogOutView();
        view.display();
    }

    public void showWrongUser() {
        view = new LoginWrongUserView();
        view.display();
    }

    public void showLoginScreen() {
        view = new LoginView();
        view.display();
    }

}
