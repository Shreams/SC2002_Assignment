package org.example.reports.models;

import java.util.ArrayList;
import java.util.Date;

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


    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRegistrationEndDate() {
        return registrationEndDate;
    }

    public void setRegistrationEndDate(Date registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCampAccessbility() {
        return campAccessbility;
    }

    public void setCampAccessbility(String campAccessbility) {
        this.campAccessbility = campAccessbility;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStaffInChargeID() {
        return staffInChargeID;
    }

    public void setStaffInChargeID(String staffInChargeID) {
        this.staffInChargeID = staffInChargeID;
    }

    public ArrayList<String> getParticipantsName() {
        return participantsName;
    }

    public void setParticipantsName(ArrayList<String> participantsName) {
        this.participantsName = participantsName;
    }

    public ArrayList<String> getCampCommitteeName() {
        return campCommitteeName;
    }

    public void setCampCommitteeName(ArrayList<String> campCommitteeName) {
        this.campCommitteeName = campCommitteeName;
    }

    public boolean isVisbility() {
        return visbility;
    }

    public void setVisbility(boolean visbility) {
        this.visbility = visbility;
    }

    public ArrayList<String> getBlacklistName() {
        return blacklistName;
    }

    public void setBlacklistName(ArrayList<String> blacklistName) {
        this.blacklistName = blacklistName;
    }
}
