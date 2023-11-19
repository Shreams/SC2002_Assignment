package org.example.camps.models;

import java.util.ArrayList;
import java.util.Date;

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
    private boolean visbility;
    private ArrayList<String> blacklist;

    public CampModel(String campName, Date[] dates, Date registrationEndDate, String location, String campAccessbility, int totalSlots, int campCommitteeSlots, String description, String staffInChargeID, boolean visbility) {
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
        this.visbility = visbility;
        this.blacklist = new ArrayList<String>();
    }

    public String getCampName() {
        return campName;
    }

    public Date[] getDates() {
        return dates;
    }

    public void setDates(Date[] dates) {
        this.dates = dates;
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

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public ArrayList<String> getCampCommittee() {
        return campCommittee;
    }

    public void setCampCommittee(ArrayList<String> campCommittee) {
        this.campCommittee = campCommittee;
    }

    public boolean isVisbility() {
        return visbility;
    }

    public void setVisbility(boolean visbility) {
        this.visbility = visbility;
    }

    public ArrayList<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(ArrayList<String> blacklist) {
        this.blacklist = blacklist;
    }

    public void addParticipant(String participantID){
        this.participants.add(participantID);
    }

    public void addCampCommittee(String campCommitteeID){
        this.campCommittee.add(campCommitteeID);
    }

    public void addStudBlacklist(String studID){
        this.blacklist.add(studID);
    }

    public void removeParticipant(String participantID){
        this.participants.remove(participantID);
    }
}