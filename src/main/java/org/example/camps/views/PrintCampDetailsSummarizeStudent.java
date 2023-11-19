package org.example.camps.views;

import org.example.camps.interfaces.ICampView;

import java.util.Date;

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
