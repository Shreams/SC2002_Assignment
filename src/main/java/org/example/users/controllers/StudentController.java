package org.example.users.controllers;

import org.example.users.interfaces.IUser;
import org.example.users.models.StudentModel;
import org.example.users.models.User;

import java.util.ArrayList;
/**
 * The {@code StudentController} class is responsible for managing student user data and interactions.
 * It implements the {@code IUser} interface to provide user-related functionality.
 * This class is part of the {@code org.example.users.controllers} package.
 *
 * @author Group1
 * @version 1.0
 */
public class StudentController implements IUser {
	/**
	 * All the student model is stored.
	 */
    private ArrayList<User> model;
    /**
     * Constructs a new {@code StudentController} with all the student model.
     *
     * @param model The list of student models.
     */
    public StudentController(ArrayList<User> model) {
        this.model = model;
   
    }

    // ########################
    // #    Points Methods    #
    // ########################
    /**
     * Gets the points of the student with the specified user ID.
     *
     * @param userID The user ID for which to get the points.
     * @return The points of the student.
     */
    public int getPoints(String userID) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                return ((StudentModel) user).getPoints();
            }
        }
        return 0; // Return 0 if user is not found or not a student
    }

    /**
     * Adds points to the student with the specified user ID.
     *
     * @param userID The user ID for which to add points.
     * @param points The points to add.
     */
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

    /**
     * Sets whether the student with the specified user ID is part of the camp committee.
     *
     * @param userID          The user ID for which to set the camp committee status.
     * @param isCampCommittee {@code true} if the student is part of the camp committee, {@code false} otherwise.
     */
    public void setIsCampCommittee(String userID, boolean isCampCommittee) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                ((StudentModel) user).setCampCommittee(isCampCommittee);
            }
        }
    }


    /**
     * Gets the actual name of the user with the specified user ID.
     *
     * @param userID The user ID for which to get the actual name.
     * @return The actual name of the user.
     */
    public String getUserActualName(String userID) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                return user.getName();
            }
        }
        return "";
    }
    /**
     * Retrieves the faculty name associated with the specified user ID.
     *
     * @param userID The user ID for which to retrieve the faculty name.
     * @return The faculty name.
     */
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
    /**
     * Retrieves user information based on the provided user ID.
     *
     * @param userID The user ID for which to retrieve information.
     * @return An array containing user information userID and password.
     */
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
    /**
     * Gets the profile information for the student with the specified user ID.
     * The information includes if the student is a part of a camp, 
     * is camp committee of a camp and the points the student has.
     *
     * @param userID          The user ID for which to get the profile.
     * @param committeeCamp   The camp for which the student is in the committee (can be empty).
     */
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
    /**
     * Sets the password for the user with the specified user ID.
     *
     * @param userID   The user ID for which to set the password.
     * @param password The new password to set.
     */
    @Override
    public void setPassword(String userID, String password) {
        for (User user : model) {
            if (user.getUserID().equals(userID)) {
                user.changePassword(password);
            }
        }
    }
    /**
     * Adds a camp to the list of camps associated with the user with the specified user ID.
     *
     * @param userID   The user ID for which to add the camp.
     * @param campName The name of the camp to add.
     */
    @Override
    public void addCampToList(String userID,String campName) {
        for(User user : model){
            if(user.getUserID().equals(userID)){
                ((StudentModel) user).addCampJoinedName(campName);
            }
        }
    }
    /**
     * Gets the list of camps associated with the user with the specified user ID.
     *
     * @param userID The user ID for which to retrieve the camp list.
     * @return An ArrayList containing the list of camps.
     */
    @Override
    public ArrayList<String> getCampList(String userID) {
        for(User user : model){
            if(user.getUserID().equals(userID)){
                return ((StudentModel) user).getCampJoinedNames();
            }
        }
        return null;
    }
    /**
     * Removes a camp from the list of camps associated with the user with the specified user ID.
     *
     * @param userID   The user ID for which to remove the camp.
     * @param campName The name of the camp to remove.
     */
    @Override
    public void removeCampFromList(String userID, String campName) {
        for(User user : model){
            if(user.getUserID().equals(userID)){
                ((StudentModel) user).removeCampJoinedName(campName);
            }
        }
    }



}
