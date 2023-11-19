package org.example.users.models;

import org.example.users.enums.UserType;

import java.util.ArrayList;

public class StaffModel extends User {

    private ArrayList<String> campCreatedNames;

    public StaffModel(String name, String userID, String facultyName, String email, UserType userType) {
        super(name, userID, facultyName, email, userType);
        this.campCreatedNames = new ArrayList<String>();
    }

    public ArrayList<String> getCampCreatedNames() {
        return campCreatedNames;
    }

    public void setCampCreatedNames(ArrayList<String> campCreatedNames) {
        this.campCreatedNames = campCreatedNames;
    }

    public void addCampCreatedName(String campName){
        this.campCreatedNames.add(campName);
    }

    public void removeCampCreatedName(String campName){
        this.campCreatedNames.remove(campName);
    }
}
