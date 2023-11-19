package org.example.filters.controllers;

import org.example.reports.models.ReportModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class FilterReportController {

    private TreeMap<String, ReportModel> reports;


    public FilterReportController(TreeMap<String, ReportModel> reports){
        this.reports = reports;
    }


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
    
    public TreeMap<String, ReportModel> filterByCampCommitteeName(String CampCommitteeName){
        HashMap<String, ReportModel> temp = new HashMap<String, ReportModel>();
        
        for (String key : reports.keySet()) {
        	ArrayList<String> CampCommitteeList = new ArrayList<>();
        	CampCommitteeList = reports.get(key).getCampCommitteeName();

        	for (String CampCommittee : CampCommitteeList) {
	            if (CampCommittee.trim().equalsIgnoreCase(CampCommitteeName.trim())) {
	                temp.put(key, reports.get(key));
	            }
        	}
        }

        TreeMap<String, ReportModel> sorted = new TreeMap<>(new CustomComparator());
        sorted.putAll(temp);

        return sorted;
    }
    
    
    public TreeMap<String, ReportModel> filterByAttendeeName(String AttendeeName){
    	
        HashMap<String, ReportModel> temp = new HashMap<String, ReportModel>();
                
        for (String key : reports.keySet()) {
        	ArrayList<String> ParticipantList = reports.get(key).getParticipantsName();
        	for (String Participant : ParticipantList) {
	            if (Participant.trim().equalsIgnoreCase(AttendeeName.trim())) {
	                temp.put(key, reports.get(key));
	            }
        	}
        }

        TreeMap<String, ReportModel> sorted = new TreeMap<>(new CustomComparator());
        sorted.putAll(temp);

        return sorted;
    }


    static class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            // case-insensitive
            // if is abc vs abcd, abc is consider first
            int result = str1.compareToIgnoreCase(str2);
            int keepComparing = 0;

            // If the strings are the same (ignoring case), compare by case-sensitive
            // natural order
            if (result == 0) {
                // keepComparing < Math.min(str1.length(), str2.length()) so dont go out of range
                while (keepComparing >= 0 && result == 0 && keepComparing < Math.min(str1.length(), str2.length())) {
                    result = Character.compare(str1.charAt(keepComparing), str2.charAt(keepComparing));
                    keepComparing++;
                }
            }
            return result;
        }
    }
}