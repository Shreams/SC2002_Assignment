package org.example.users.models;

import org.example.users.enums.UserType;

import java.util.ArrayList;
/**
 * The {@code StudentModel} class represents a student user in the system.
 * It extends the {@code User} class and includes additional information such as points,
 * camp committee status, and a list of joined camps.
 * This class is part of the {@code org.example.users.models} package.
 *
 * @author Group1
 * @version 1.0
 */
public class StudentModel extends User {
	/**
	 * The lists of all camp names a student joined. 
	 */
    private ArrayList<String> campJoinedNames;
    /**
     * The points a student has.
     */
    private int points;
    /**
     * Whether a student is a camp committee. 
     */
    private boolean isCampcommittee;
    /**
     * Constructs a new student model with the specified name, user ID, faculty name, email, and user type.
     *
     * @param name        The name of the student.
     * @param userID      The user ID.
     * @param facultyName The faculty name of the student.
     * @param email       The email of the student.
     * @param userType    The user type (student).
     */
    public StudentModel(String name, String userID, String facultyName, String email, UserType userType) {
        super(name, userID, facultyName, email, userType);
        points = 0;
        isCampcommittee = false;
        this.campJoinedNames = new ArrayList<String>();
    }
    /**
     * Gets the points of the student.
     *
     * @return The points of the student.
     */
    public int getPoints() {
        return points;
    }
    /**
     * Sets the points of the student.
     *
     * @param points The new points to set.
     */
    public void setPoints(int points) {
        this.points = points;
    }
    /**
     * Adds points to the student.
     *
     * @param points The points to add.
     */
    public void addPoints(int points){
        this.points += points;
    }
    /**
     * Removes points from the student.
     *
     * @param points The points to remove.
     */
    public void removePoints(int points){
        this.points -= points;
    }
    /**
     * Checks if the student is a camp committee.
     *
     * @return {@code true} if the student is a camp committee, {@code false} otherwise.
     */
    public boolean isCampCommittee() {
        return isCampcommittee;
    }
    /**
     * Sets whether the student is a camp committee.
     *
     * @param campCommittee {@code true} if the student is a camp committee, {@code false} otherwise.
     */
    public void setCampCommittee(boolean campCommittee) {
        isCampcommittee = campCommittee;
    }
    /**
     * Gets the list of camp names joined by the student.
     *
     * @return An ArrayList containing the names of joined camps.
     */
    public ArrayList<String> getCampJoinedNames() {
        return campJoinedNames;
    }
    /**
     * Sets the list of camp names joined by the student.
     *
     * @param campJoinedNames The new list of camp names.
     */
    public void setCampJoinedNames(ArrayList<String> campJoinedNames) {
        this.campJoinedNames = campJoinedNames;
    }
    /**
     * Removes a camp name from the list of joined camps.
     *
     * @param campName The name of the camp to remove.
     */
    public void removeCampJoinedName(String campName){
        this.campJoinedNames.remove(campName);
    }

    /**
     * Adds a camp name to the list of joined camps.
     *
     * @param campName The name of the camp to add.
     */
    public void addCampJoinedName(String campName){
        this.campJoinedNames.add(campName);
    }
}
