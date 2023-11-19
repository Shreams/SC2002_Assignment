package org.example.users.controllers;

import org.example.camps.interfaces.ICampController;
import org.example.users.interfaces.IUser;
import org.example.users.models.StudentModel;
import org.example.users.models.User;
import org.example.users.views.StudentView;

import java.util.ArrayList;

public class StudentController implements IUser {

    private ArrayList<User> model;
    private StudentView view;

    public StudentController(ArrayList<User> model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    // ########################
    // #    Points Methods    #
    // ########################

    public int getPoints(String userID) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                return ((StudentModel) user).getPoints();
            }
        }
        return 0; // Return 0 if user is not found or not a student
    }


    public void addPoints(String userID, int points) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                ((StudentModel) user).addPoints(points);
            }
        }
    }


    // ####################
    // #    Accessor      #
    // ####################


    public void setIsCampCommittee(String userID, boolean isCampCommittee) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                ((StudentModel) user).setCampCommittee(isCampCommittee);
            }
        }
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

    public void getProfile(String userID,String committeeCamp) {
        ArrayList<String> campRegistered= getCampList(userID);
        int points=0;
        System.out.println("Profile:");
        //No camp return
        if(campRegistered.isEmpty())
        {
            System.out.println("Not in any camps\n");
            return;
        }

        //get points
        for (User user : model) {
            if (user.getUserID().equals(userID))
                points=((StudentModel) user).getPoints();
        }

        System.out.println("Camps Registered\n");
        //print camps the person is in
        for (String camps : campRegistered)
        {
            System.out.println(camps+"\n");

        }
        //Print camp he is in for committee if any
        if(!committeeCamp.isEmpty())
        {System.out.println("Committee in camp "+committeeCamp+"\n");
            System.out.println("Points:"+points+"\n");
        }
    }


    // ************************* Implementation *************************

    @Override
    public void setPassword(String userID, String password) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                user.changePassword(password);
            }
        }
    }

    @Override
    public void addCampToList(String userID,String campName) {
        for(User user : model){
            if(user.getUserID().equals(userID)){
                ((StudentModel) user).addCampJoinedName(campName);
            }
        }
    }

    @Override
    public ArrayList<String> getCampList(String userID) {
        for(User user : model){
            if(user.getUserID().equals(userID)){
                return ((StudentModel) user).getCampJoinedNames();
            }
        }
        return null;
    }

    @Override
    public void removeCampFromList(String userID, String campName) {
        for(User user : model){
            if(user.getUserID().equals(userID)){
                ((StudentModel) user).removeCampJoinedName(campName);
            }
        }
    }



}
