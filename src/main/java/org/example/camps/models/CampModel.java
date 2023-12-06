package org.example.camps.models;

import java.util.ArrayList;
import java.util.Date;


/**
 * This class represents a Camp Model containing details and properties of a camp.
 *
 * @author Group1
 * @version 1.0
 */
public class CampModel {
    private String campName;
    private Date[] dates;

    private Date registrationEndDate;
    private String location;
    private String campAccessbility;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private String staffInChargeID;
    private ArrayList<String> participants;

    private ArrayList<String> campCommittee;
    private boolean visibility;
    private ArrayList<String> blacklist;




    /**
     * Constructs a CampModel object with the provided details.
     *
     * @param campName            The name of the camp.
     * @param dates               An array of Date objects representing camp dates.
     * @param registrationEndDate The registration end date for the camp.
     * @param location            The location of the camp.
     * @param campAccessbility    The accessibility of the camp.
     * @param totalSlots          The total available slots in the camp.
     * @param campCommitteeSlots  The number of slots allocated for the camp committee.
     * @param description         The description of the camp.
     * @param staffInChargeID     The ID of the staff member in charge of the camp.
     * @param visibility          The visibility status of the camp.
     */
    public CampModel(String campName, Date[] dates, Date registrationEndDate, String location, String campAccessbility, int totalSlots, int campCommitteeSlots, String description, String staffInChargeID, boolean visibility) {
        this.campName = campName;
        this.dates = dates;
        this.registrationEndDate = registrationEndDate;
        this.location = location;
        this.campAccessbility = campAccessbility;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInChargeID = staffInChargeID;
        this.participants = new ArrayList<String>();
        this.campCommittee = new ArrayList<String>();
        this.visibility = visibility;
        this.blacklist = new ArrayList<String>();
    }


    /**
     * Gets the name of the camp.
     *
     * @return The name of the camp.
     */
    public String getCampName() {
        return campName;
    }


    /**
     * Gets the array of dates associated with the camp.
     *
     * @return An array of Date objects representing camp dates.
     */
    public Date[] getDates() {
        return dates;
    }

    /**
     * Sets the array of dates associated with the camp.
     *
     * @param dates An array of Date objects representing camp dates.
     */
    /**
     * Sets the array of dates associated with the camp.
     *
     * @param dates An array of Date objects representing camp dates.
     */
    public void setDates(Date[] dates) {
        this.dates = dates;
    }

    /**
     * Gets the registration end date of the camp.
     *
     * @return The registration end date of the camp.
     */
    public Date getRegistrationEndDate() {
        return registrationEndDate;
    }

    /**
     * Sets the registration end date of the camp.
     *
     * @param registrationEndDate The registration end date of the camp.
     */
    public void setRegistrationEndDate(Date registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    /**
     * Gets the location of the camp.
     *
     * @return The location of the camp.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the camp.
     *
     * @param location The location of the camp.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the accessibility of the camp.
     *
     * @return The accessibility of the camp.
     */
    public String getCampAccessbility() {
        return campAccessbility;
    }

    /**
     * Sets the accessibility of the camp.
     *
     * @param campAccessbility The accessibility of the camp.
     */
    public void setCampAccessbility(String campAccessbility) {
        this.campAccessbility = campAccessbility;
    }

    /**
     * Gets the total slots available in the camp.
     *
     * @return The total slots available in the camp.
     */
    public int getTotalSlots() {
        return totalSlots;
    }

    /**
     * Sets the total slots available in the camp.
     *
     * @param totalSlots The total slots available in the camp.
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    /**
     * Gets the number of camp committee slots in the camp.
     *
     * @return The number of camp committee slots in the camp.
     */
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    /**
     * Sets the number of camp committee slots in the camp.
     *
     * @param campCommitteeSlots The number of camp committee slots in the camp.
     */
    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    /**
     * Gets the description of the camp.
     *
     * @return The description of the camp.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the camp.
     *
     * @param description The description of the camp.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the staff ID in charge of the camp.
     *
     * @return The staff ID in charge of the camp.
     */
    public String getStaffInChargeID() {
        return staffInChargeID;
    }


    /**
     * Sets the staff ID in charge of the camp.
     *
     * @param staffInChargeID The staff ID in charge of the camp.
     */
    public void setStaffInChargeID(String staffInChargeID) {
        this.staffInChargeID = staffInChargeID;
    }

    /**
     * Gets the list of participants in the camp.
     *
     * @return The list of participants in the camp.
     */
    public ArrayList<String> getParticipants() {
        return participants;
    }

    /**
     * Sets the list of participants in the camp.
     *
     * @param participants The list of participants in the camp.
     */
    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    /**
     * Gets the list of camp committee members in the camp.
     *
     * @return The list of camp committee members in the camp.
     */
    public ArrayList<String> getCampCommittee() {
        return campCommittee;
    }

    /**
     * Sets the list of camp committee members in the camp.
     *
     * @param campCommittee The list of camp committee members in the camp.
     */
    public void setCampCommittee(ArrayList<String> campCommittee) {
        this.campCommittee = campCommittee;
    }

    /**
     * Checks the visibility of the camp.
     *
     * @return The visibility status of the camp.
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     * Sets the visibility of the camp.
     *
     * @param visbility The visibility status of the camp.
     */
    public void setVisibility(boolean visbility) {
        this.visibility = visbility;
    }

    /**
     * Gets the list of blacklisted participants in the camp.
     *
     * @return The list of blacklisted participants in the camp.
     */
    public ArrayList<String> getBlacklist() {
        return blacklist;
    }

    /**
     * Sets the list of blacklisted participants in the camp.
     *
     * @param blacklist The list of blacklisted participants in the camp.
     */
    public void setBlacklist(ArrayList<String> blacklist) {
        this.blacklist = blacklist;
    }


    /**
     * Adds a participant to the camp.
     *
     * @param participantID The ID of the participant to be added.
     */
    public void addParticipant(String participantID) {
        this.participants.add(participantID);
    }

    /**
     * Adds a member to the camp committee.
     *
     * @param campCommitteeID The ID of the committee member to be added.
     */
    public void addCampCommittee(String campCommitteeID) {
        this.campCommittee.add(campCommitteeID);
    }

    /**
     * Adds a student to the camp blacklist.
     *
     * @param studID The ID of the student to be added to the blacklist.
     */
    public void addStudBlacklist(String studID) {
        this.blacklist.add(studID);
    }

    /**
     * Removes a participant from the camp.
     *
     * @param participantID The ID of the participant to be removed.
     */
    public void removeParticipant(String participantID) {
        this.participants.remove(participantID);
    }
}