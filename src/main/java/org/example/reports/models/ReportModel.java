package org.example.reports.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Group1
 * @version 1.0
 * 
 * The ReportModel class represents a model for generating reports about a camp.
 * It contains various attributes and methods to access and modify the camp's information.
 */
public class ReportModel {

    // This is basically a replica of the CampModel
    // Just that participant and campcommittee will be extracted to be the name
    // staffInChargeID will be the name of the staff in charge

    private String campName;
    private Date startDate;

    private Date endDate;

    private Date registrationEndDate;
    private String location;
    private String campAccessbility;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private String staffInChargeID;
    private ArrayList<String> participantsName;

    private ArrayList<String> campCommitteeName;
    private boolean visbility;
    private ArrayList<String> blacklistName;
    /**
     * Initializes a new instance of the ReportModel class with the specified parameters.
     *
     * @param campName            The name of the camp.
     * @param startDate           The start date of the camp.
     * @param endDate             The end date of the camp.
     * @param registrationEndDate The registration end date for the camp.
     * @param location            The location of the camp.
     * @param campAccessbility    The accessibility information of the camp.
     * @param totalSlots          The total number of slots available for the camp.
     * @param campCommitteeSlots  The number of slots reserved for the camp committee.
     * @param description         The description of the camp.
     * @param staffInChargeID     The ID of the staff in charge of the camp.
     * @param visbility           The visibility status of the camp.
     * @param participantsName    A list of participant names for the camp.
     * @param campCommitteeName   A list of camp committee member names.
     * @param blacklistName       A list of names on the camp's blacklist.
     */
    public ReportModel(String campName, Date startDate, Date endDate, Date registrationEndDate, String location, String campAccessbility, int totalSlots, int campCommitteeSlots, String description, String staffInChargeID, boolean visbility, ArrayList<String> participantsName, ArrayList<String> campCommitteeName, ArrayList<String> blacklistName) {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationEndDate = registrationEndDate;
        this.location = location;
        this.campAccessbility = campAccessbility;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInChargeID = staffInChargeID;
        this.visbility = visbility;
        this.participantsName = participantsName;
        this.campCommitteeName = campCommitteeName;
        this.blacklistName = blacklistName;
    }


    /**
     * Get the name of the camp.
     *
     * @return The camp's name.
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Set the name of the camp.
     *
     * @param campName The camp's name to set.
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Get the start date of the camp.
     *
     * @return The camp's start date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of the camp.
     *
     * @param startDate The camp's start date to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the end date of the camp event.
     *
     * @return The end date of the camp event.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the end date of the camp event.
     *
     * @param endDate The end date of the camp event.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the registration end date of the camp event.
     *
     * @return The registration end date of the camp event.
     */
    public Date getRegistrationEndDate() {
        return registrationEndDate;
    }

    /**
     * Set the registration end date of the camp event.
     *
     * @param registrationEndDate The registration end date of the camp event.
     */
    public void setRegistrationEndDate(Date registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    
    /**
     * Get the location of the camp event.
     *
     * @return The location of the camp event.
     */
    public String getLocation() {
        return location;
    }

    
    /**
     * Set the location of the camp event.
     *
     * @param location The location of the camp event.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the accessibility information of the camp event.
     *
     * @return The accessibility information of the camp event.
     */
    public String getCampAccessbility() {
        return campAccessbility;
    }

    
    /**
     * Set the accessibility information of the camp event.
     *
     * @param campAccessbility The accessibility information of the camp event.
     */
    public void setCampAccessbility(String campAccessbility) {
        this.campAccessbility = campAccessbility;
    }

    /**
     * Get the total number of slots available for the camp event.
     *
     * @return The total number of slots available for the camp event.
     */
    public int getTotalSlots() {
        return totalSlots;
    }

    /**
     * Set the total number of slots available for the camp event.
     *
     * @param totalSlots The total number of slots available for the camp event.
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    /**
     * Get the number of slots reserved for the camp committee.
     *
     * @return The number of slots reserved for the camp committee.
     */
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    /**
     * Set the number of slots reserved for the camp committee.
     *
     * @param campCommitteeSlots The number of slots reserved for the camp committee.
     */
    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    /**
     * Get the description of the camp event.
     *
     * @return The description of the camp event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the camp event.
     *
     * @param description The description of the camp event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the ID of the staff in charge of the camp event.
     *
     * @return The ID of the staff in charge of the camp event.
     */
    public String getStaffInChargeID() {
        return staffInChargeID;
    }

    /**
     * Set the ID of the staff in charge of the camp event.
     *
     * @param staffInChargeID The ID of the staff in charge of the camp event.
     */
    public void setStaffInChargeID(String staffInChargeID) {
        this.staffInChargeID = staffInChargeID;
    }

    /**
     * Get the list of participants' names for the camp event.
     *
     * @return The list of participants' names for the camp event.
     */
    public ArrayList<String> getParticipantsName() {
        return participantsName;
    }

    /**
     * Set the list of participants' names for the camp event.
     *
     * @param participantsName The list of participants' names for the camp event.
     */
    public void setParticipantsName(ArrayList<String> participantsName) {
        this.participantsName = participantsName;
    }

    /**
     * Get the list of camp committee members' names for the camp event.
     *
     * @return The list of camp committee members' names for the camp event.
     */
    public ArrayList<String> getCampCommitteeName() {
        return campCommitteeName;
    }

    
    /**
     * Set the list of camp committee members' names for the camp event.
     *
     * @param campCommitteeName The list of camp committee members' names for the camp event.
     */
    public void setCampCommitteeName(ArrayList<String> campCommitteeName) {
        this.campCommitteeName = campCommitteeName;
    }

    /**
     * Check if the camp is visible or not.
     *
     * @return True if the camp is visible, false otherwise.
     */
    public boolean isVisbility() {
        return visbility;
    }

    /**
     * Set the visibility status of the camp.
     *
     * @param visbility The visibility status to set (true for visible, false for not visible).
     */
    public void setVisbility(boolean visbility) {
        this.visbility = visbility;
    }

    /**
     * Get the list of names of participants in the camp.
     *
     * @return ArrayList containing names of participants.
     */
    public ArrayList<String> getBlacklistName() {
        return blacklistName;
    }

    /**
     * Set the blacklist of names of participants in the camp.
     *
     * @param blacklistName ArrayList of participant names to set.
     */
    public void setBlacklistName(ArrayList<String> blacklistName) {
        this.blacklistName = blacklistName;
    }
}
