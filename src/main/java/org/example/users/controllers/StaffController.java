package org.example.users.controllers;

import org.example.camps.interfaces.ICampController;
import org.example.users.interfaces.IUser;
import org.example.users.models.StaffModel;
import org.example.users.models.User;
import org.example.users.views.StaffView;

import java.util.ArrayList;
import java.util.Objects;

public class StaffController implements IUser {

    private ArrayList<User> model;

    private StaffView view;

    public StaffController(ArrayList<User> model, StaffView view) {
        this.model = model;
        this.view = view;
    }


    // ########################
    // #  Accessor & Mutator  #
    // ########################

    @Override
    public void removeCampFromList(String userID, String campName) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                ((StaffModel) user).removeCampCreatedName(campName);
            }
        }
    }

    @Override
    public void setPassword(String userID,String password) {

        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                user.changePassword(password);
            }
        }
    }

    @Override
    public void addCampToList(String userID, String campName) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                ((StaffModel) user).addCampCreatedName(campName);
            }
        }
    }

    @Override
    public ArrayList<String> getCampList(String userID) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                return ((StaffModel) user).getCampCreatedNames();
            }
        }
        return new ArrayList<>();
    }

    public String getUserActualName(String userID) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                return user.getName();
            }
        }
        return "";
    }

    @Override
    public String getUserFacultyName(String userID) {

        if (!model.isEmpty()) {
            for (User user : model) {
                if (user.getUserID().equals(userID)) {
                    return user.getFacultyName();
                }
            }
        }
        return "";
    }

    @Override
    public String[] retrieveUser(String userID) {

        if(!model.isEmpty()) {
            for (User user : model) {
                if (user.getUserID().equals(userID)) {
                    return new String[]{user.getUserID(), user.getPassword()};
                }
            }
        }

        // Return empty string if user is not found
        return new String[0];
    }
}
