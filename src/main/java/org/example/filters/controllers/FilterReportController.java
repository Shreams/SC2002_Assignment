package org.example.filters.controllers;

import org.example.filters.utilities.CustomComparator;
import org.example.reports.models.ReportModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * The FilterReportController class manages filtering operations on ReportModel objects.
 * This controller takes a TreeMap of ReportModel objects and provides methods to filter reports.
 *
 * @author Group1
 * @version 1.0
 */
public class FilterReportController {

    private TreeMap<String, ReportModel> reports;


    /**
     * Constructs a FilterReportController with a TreeMap of ReportModel objects.
     *
     * @param reports A TreeMap containing ReportModel objects.
     */
    public FilterReportController(TreeMap<String, ReportModel> reports){
        this.reports = reports;
    }


    /**
     * Filters the reports by camp name.
     *
     * @param campName The name of the camp to filter the reports.
     * @return A TreeMap containing filtered reports sorted by a custom comparator.
     */
    public TreeMap<String, ReportModel> filterByCampName(String campName){
        HashMap<String, ReportModel> temp = new HashMap<String, ReportModel>();

        for (String key : reports.keySet()) {
            if (reports.get(key).getCampName().equalsIgnoreCase(campName.trim())) {
                temp.put(key, reports.get(key));
                
            }
        }
        
        TreeMap<String, ReportModel> sorted = new TreeMap<>(new CustomComparator());
        sorted.putAll(temp);
        
        return sorted;
    }

    /**
     * Filters the reports by camp committee name.
     *
     * @param campCommitteeName The name of the camp committee to filter the reports.
     * @return A TreeMap containing filtered reports sorted by a custom comparator.
     */
    public TreeMap<String, ReportModel> filterByCampCommitteeName(String campCommitteeName){
        HashMap<String, ReportModel> temp = new HashMap<String, ReportModel>();
        
        for (String key : reports.keySet()) {
        	ArrayList<String> CampCommitteeList = new ArrayList<>();
        	CampCommitteeList = reports.get(key).getCampCommitteeName();

        	for (String CampCommittee : CampCommitteeList) {
	            if (CampCommittee.trim().equalsIgnoreCase(campCommitteeName.trim())) {
	                temp.put(key, reports.get(key));
	            }
        	}
        }

        TreeMap<String, ReportModel> sorted = new TreeMap<>(new CustomComparator());
        sorted.putAll(temp);

        return sorted;
    }


    /**
     * Filters the reports by attendee name.
     *
     * @param attendeeName The name of the attendee to filter the reports.
     * @return A TreeMap containing filtered reports sorted by a custom comparator.
     */
    public TreeMap<String, ReportModel> filterByAttendeeName(String attendeeName){
    	
        HashMap<String, ReportModel> temp = new HashMap<String, ReportModel>();
                
        for (String key : reports.keySet()) {
        	ArrayList<String> ParticipantList = reports.get(key).getParticipantsName();
        	for (String Participant : ParticipantList) {
	            if (Participant.trim().equalsIgnoreCase(attendeeName.trim())) {
	                temp.put(key, reports.get(key));
	            }
        	}
        }

        TreeMap<String, ReportModel> sorted = new TreeMap<>(new CustomComparator());
        sorted.putAll(temp);

        return sorted;
    }


}