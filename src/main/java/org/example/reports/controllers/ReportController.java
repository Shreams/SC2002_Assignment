package org.example.reports.controllers;

import org.example.reports.interfaces.IReport;
import org.example.reports.models.ReportModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

public class ReportController {

    // Basically 1 excel will include all the camps

    //ID is the same as campID
    private TreeMap<String, ReportModel> models;

    public ReportController(){
        this.models = new TreeMap<String, ReportModel>();
    }


    // ###################################
    // #    Generation Report Methods    #
    // ###################################

    public void generateReportOfAttendees(IReport report){
        report.generateReport(models);
    }

    public void generateReportOfCampCommittee(IReport report, HashMap<String, Integer> points){
        report.generateCampCommitteeReport(models, points);
    }


    // ###############################
    // #    Report Model Methods     #
    // ###############################

    public void addCampDetailsToReport(String campName, Date startDate, Date endDate, Date registrationEndDate, String location, String campAccessbility, int totalSlots, int campCommitteeSlots, String description, String staffInChargeID, boolean visbility, ArrayList<String> participants, ArrayList<String> campCommittee, ArrayList<String> blacklist) {
        ReportModel model = new ReportModel(campName, startDate, endDate, registrationEndDate, location, campAccessbility, totalSlots, campCommitteeSlots, description, staffInChargeID, visbility, participants, campCommittee, blacklist);
        models.put(campName, model);
    }






        // This will generate a txt/csv file of performance the camp committee

}
