package org.example.camps.views;

import org.example.camps.interfaces.ICampView;

import java.util.Date;

/**
 * This class represents a summarized view of camp details from a student's perspective.
 * It implements the ICampView interface for displaying camp information.
 *
 * @author Group1
 * @version 1.0
 */
public class PrintCampDetailsSummarizeStudent implements ICampView {

    private String campName;
    private String campLocation;
    private Date startDate;
    private Date endDate;
    private String duration;

    private String role;

    private Date registrationEndDate;
    private int remainingSlots;
    private int remainingCommitteeSlots;


    /**
     * Initializes the PrintCampDetailsSummarizeStudent object with student-specific camp details for summarizing information.
     *
     * @param campName             The name of the camp.
     * @param campLocation         The location of the camp.
     * @param startDate            The start date of the camp.
     * @param endDate              The end date of the camp.
     * @param registrationEndDate  The registration end date for the camp.
     * @param remainingSlots       The remaining participant slots (total slots - total committed slots - number of participants).
     * @param remainingCommitteeSlots  The remaining committee slots.
     * @param role                 The role of the student in the camp.
     */
    public PrintCampDetailsSummarizeStudent(String campName, String campLocation, Date startDate, Date endDate, Date registrationEndDate, int remainingSlots, int remainingCommitteeSlots, String role) {
        this.campName = campName;
        this.campLocation = campLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationEndDate = registrationEndDate;
        this.remainingSlots = remainingSlots; // Do note that the remaining slots here is the total slots - total committed slots - number of participants
        this.remainingCommitteeSlots = remainingCommitteeSlots;
    }

    @Override
    public void display() {

        // Get the days difference between start and end date
        long diff = endDate.getTime() - startDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        // Convert the days difference to string
        duration = Long.toString(diffDays);


        System.out.println("Camp Name: " + campName);
        System.out.println("Camp Location: " + campLocation);
        System.out.println("Camp Start Date: " + startDate);
        System.out.println("Camp End Date: " + endDate);
        System.out.println("Camp Duration (days): " + duration);
        System.out.println("Registration End Date: " + registrationEndDate);
        System.out.println("Remaining Participant Slots: " + remainingSlots);
        System.out.println("Role: " + role);
        System.out.println("Remaining Committee Slots: " + remainingCommitteeSlots);

        System.out.println();

    }
}
