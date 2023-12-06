package org.example.camps.views;

import org.example.camps.interfaces.ICampView;

import java.util.ArrayList;
import java.util.Date;


/**
 * Represents a view to print camp details.
 * Implements the ICampView interface.
 *
 * @author Group1
 * @version 1.0
 */
public class PrintCampDetails implements ICampView {

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

    private int remainingSlots;
    private int remainingCommitteeSlots;




    /**
     * Constructor for PrintCampDetails class.
     *
     * @param campName            The name of the camp.
     * @param dates               An array containing start and end dates of the camp.
     * @param registrationEndDate The registration end date for the camp.
     * @param location            The location of the camp.
     * @param campAccessbility     The accessibility of the camp.
     * @param totalSlots          The total number of slots available for participants.
     * @param campCommitteeSlots  The number of slots for the camp committee.
     * @param description         The description of the camp.
     * @param staffInChargeID     The ID of the staff in charge of the camp.
     * @param visbility           The visibility status of the camp.
     * @param participants        The list of participants in the camp.
     * @param campCommittee       The list of members in the camp committee.
     * @param blacklist           The list of blacklisted participants.
     */
    public PrintCampDetails(String campName, Date[] dates, Date registrationEndDate, String location, String campAccessbility, int totalSlots, int campCommitteeSlots, String description, String staffInChargeID, boolean visbility, ArrayList<String> participants, ArrayList<String> campCommittee, ArrayList<String> blacklist) {
        this.campName = campName;
        this.dates = dates;
        this.registrationEndDate = registrationEndDate;
        this.location = location;
        this.campAccessbility = campAccessbility;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInChargeID = staffInChargeID;
        this.participants = participants;
        this.campCommittee = campCommittee;
        this.visbility = visbility;
        this.blacklist = blacklist;
        this.remainingSlots = totalSlots - participants.size() - campCommitteeSlots;
        this.remainingCommitteeSlots = campCommitteeSlots - campCommittee.size();
    }




    @Override
    public void display() {
        System.out.println("\n~~Camp Details~~");
        System.out.println("Camp Name: " + campName);
        System.out.println("Description: " + description);
        System.out.println("Start Dates: " + dates[0]);
        System.out.println("End Dates: " + dates[1]);
        System.out.println("Registration End Date: " + registrationEndDate);
        System.out.println("Location: " + location);
        System.out.println("Camp Accessbility: " + campAccessbility);
        System.out.println("Visbility: " + visbility);
        System.out.println("Staff In Charge ID: " + staffInChargeID);
        System.out.println("Total Slots: " + totalSlots);
        System.out.println("Camp Committee Slots: " + campCommitteeSlots);
        System.out.println("Remaining Participant Slots: " + remainingSlots);
        System.out.println("Remaining Committee Slots: " + remainingCommitteeSlots);


        System.out.println("Participants: ");
        if (!participants.isEmpty()){
            for (String participant : participants) {
                System.out.println(participant);
            }
        } else {
            System.out.println("No participants");
        }


        System.out.println("Camp Committee: ");
        if (!campCommittee.isEmpty()){
            for (String committee : campCommittee) {
                System.out.println(committee);
            }
        } else {
            System.out.println("No camp committee");
        }

        System.out.println("Blacklist: ");
        if(!blacklist.isEmpty()){
            for (String blacklisted : blacklist) {
                System.out.println(blacklisted);
            }
        } else {
            System.out.println("No blacklisted participants");
        }

        System.out.println();


    }
}
