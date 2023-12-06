package org.example.reports.controllers;

import org.example.reports.models.ReportModel;
import org.example.reports.utils.GenerateReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Group1
 * @version 1.0
 * 
 *
 * The ReportController class is responsible for managing and generating various reports related to camps.
 * It stores camp details in a TreeMap and provides methods to generate reports for attendees and camp committees.
 */
public class ReportController {

    // Basically 1 excel will include all the camps

    //ID is the same as campID
    private TreeMap<String, ReportModel> models;

    /**
     * Constructs a new ReportController instance.
     * 
     * This constructor initializes the controller with an empty TreeMap
     * to store ReportModel objects.
     */
    public ReportController(){
        this.models = new TreeMap<String, ReportModel>();
    }


    // ###################################
    // #    Generation Report Methods    #
    // ###################################

    /**
     * Generates a report of attendees using the provided IReport implementation.
     *
     * @param report The IReport implementation responsible for generating the report.
     */
    public void generateReportOfAttendees(GenerateReport report){
        report.generateReport(models);
    }

    /**
     * Generates a report of camp committee members with associated points using the provided IReport implementation.
     *
     * @param report The IReport implementation responsible for generating the report.
     * @param points A HashMap containing camp committee members' IDs and their associated points.
     */
    public void generateReportOfCampCommittee(GenerateReport report, HashMap<String, Integer> points){
        report.generateCampCommitteeReport(models, points);
    }


    // ###############################
    // #    Report Model Methods     #
    // ###############################
    
    /**
     * Adds camp details to a report and stores them in the internal data structure.
     *
     * @param campName           The name of the camp.
     * @param startDate          The start date of the camp.
     * @param endDate            The end date of the camp.
     * @param registrationEndDate The registration end date for the camp.
     * @param location           The location of the camp.
     * @param campAccessbility   The accessibility information for the camp.
     * @param totalSlots         The total number of slots available in the camp.
     * @param campCommitteeSlots The number of slots reserved for camp committee members.
     * @param description        A description of the camp.
     * @param staffInChargeID    The ID of the staff member in charge of the camp.
     * @param visbility         The visibility status of the camp in the report.
     * @param participants       A list of participant names for the camp.
     * @param campCommittee      A list of camp committee member names.
     * @param blacklist          A list of names of individuals on the camp's blacklist.
     */
    public void addCampDetailsToReport(String campName, Date startDate, Date endDate, Date registrationEndDate, String location, String campAccessbility, int totalSlots, int campCommitteeSlots, String description, String staffInChargeID, boolean visbility, ArrayList<String> participants, ArrayList<String> campCommittee, ArrayList<String> blacklist) {
        ReportModel model = new ReportModel(campName, startDate, endDate, registrationEndDate, location, campAccessbility, totalSlots, campCommitteeSlots, description, staffInChargeID, visbility, participants, campCommittee, blacklist);
        models.put(campName, model);
    }




        // This will generate a txt/csv file of performance the camp committee

}
