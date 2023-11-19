package org.example.users.interfaces;

import java.util.ArrayList;

public interface IUser {

    public String[] retrieveUser(String userID);

    public void setPassword(String userID, String password);

    public String getUserFacultyName(String userID);

    public void addCampToList(String userID, String campName);

    public void removeCampFromList(String userID, String campName);

    public ArrayList<String> getCampList(String userID);
}
