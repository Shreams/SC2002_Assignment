package org.example.users.models;

import org.example.users.enums.UserType;

import java.util.ArrayList;

public class StudentModel extends User {

    private ArrayList<String> campJoinedNames;

    private int points;

    private boolean isCampcommittee;

    public StudentModel(String name, String userID, String facultyName, String email, UserType userType) {
        super(name, userID, facultyName, email, userType);
        points = 0;
        isCampcommittee = false;
        this.campJoinedNames = new ArrayList<String>();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points){
        this.points += points;
    }

    public void removePoints(int points){
        this.points -= points;
    }

    public boolean isCampCommittee() {
        return isCampcommittee;
    }

    public void setCampCommittee(boolean campCommittee) {
        isCampcommittee = campCommittee;
    }

    public ArrayList<String> getCampJoinedNames() {
        return campJoinedNames;
    }

    public void setCampJoinedNames(ArrayList<String> campJoinedNames) {
        this.campJoinedNames = campJoinedNames;
    }


    public void removeCampJoinedName(String campName){
        this.campJoinedNames.remove(campName);
    }


    public void addCampJoinedName(String campName){
        this.campJoinedNames.add(campName);
    }
}
