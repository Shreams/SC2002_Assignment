package org.example.reports.utils;

import org.example.filters.controllers.FilterReportController;
import org.example.reports.interfaces.IReport;
import org.example.reports.models.ReportModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class GenerateTxt implements IReport {

	@Override
	public void generateCampCommitteeReport(TreeMap<String, ReportModel> reportModel, HashMap<String, Integer> points) {
		System.out.println("Generating TXT report for Camp Committee...");

		int pointsindex = 0;
	    Scanner scanner = new Scanner(System.in);
	    while (true) {
	        try {
	            String currentDirectory = System.getProperty("user.dir");
	            String relativeFilePath = "Reports_gen\\camp_committee_report.txt";
	            String filePath = currentDirectory + "/" + relativeFilePath;

	            // Ensure that the directory exists
	            File directory = new File(filePath).getParentFile();
	            if (!directory.exists()) {
	                // Create the directory and its parent directories if they don't exist
	                if (directory.mkdirs()) {
	                    System.out.println("Directory created successfully.");
	                } else {
	                    System.out.println("Failed to create directory.");
	                    return; // Exit the program if directory creation fails
	                }
	            }

	            FileWriter fileWriter = new FileWriter(filePath); // WILL GIVE ERROR IF PATH DOES NOT EXIST

	            // Create a BufferedWriter to efficiently write to the file
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	            if (!reportModel.isEmpty()) {
	            // Write text to the file
	            bufferedWriter.write("Camp managed by : " + reportModel.get(reportModel.keySet().toArray()[0]).getStaffInChargeID());
	            bufferedWriter.newLine();
	            bufferedWriter.newLine();

	            for (String campName : reportModel.keySet()) {
	                bufferedWriter.write("Camp Name : " + campName);
	                bufferedWriter.newLine();
	                bufferedWriter.write("Camp Committee Members: ");
	                bufferedWriter.newLine();

	                Collections.sort(reportModel.get(campName).getCampCommitteeName());

	                for (String committeeMember : reportModel.get(campName).getCampCommitteeName()) {
	                    if (committeeMember != null ) {
	                        bufferedWriter.write(committeeMember);
	                        bufferedWriter.write(", Points: ");
	                        bufferedWriter.write(String.valueOf(points.get(committeeMember)));
	                        bufferedWriter.newLine();
	                    }
	                }

	                bufferedWriter.newLine();
	            }

	            bufferedWriter.close();
	            System.out.println("Camp Committee report has been written to the file successfully.");
	            }

	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	            e.printStackTrace();
	        }
	        break;
	    }
	}

    @Override
    public void generateReport(TreeMap<String, ReportModel> reportModel) {
        System.out.println("Generating TXT report...");

        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                String currentDirectory = System.getProperty("user.dir");
                String relativeFilePath = "Reports_gen\\report.txt";
                String filePath = currentDirectory + "/" + relativeFilePath;
                
                // Ensure that the directory exists
                File directory = new File(filePath).getParentFile();
                if (!directory.exists()) {
                    // Create the directory and its parent directories if they don't exist
                    if (directory.mkdirs()) {
                        System.out.println("Directory created successfully.");
                    } else {
                        System.out.println("Failed to create directory.");
                        return; // Exit the program if directory creation fails
                    }
                }

                System.out.println("How do you want to filter by?");
                System.out.println("1. Camp Name");
                System.out.println("2. Camp Committee");
                System.out.println("3. Participants");
                System.out.println("4. View All Under You");
                int choice = scanner.nextInt();
                // Consume remaining newline character in the buffer
                scanner.nextLine();
  
                switch (choice) {
	                case 1 -> {
	                	System.out.println("Enter Camp Name to filter:");
	                    String campName = scanner.nextLine();
	                	
	                	TreeMap<String, ReportModel> filterCampModel = new FilterReportController(reportModel).filterByCampName(campName);
	                	reportModel = filterCampModel ;//new HashMap<>(filterCampModel);
	                    
	                    break;
	                }
	
	                case 2 -> {
	                	System.out.println("Enter Camp Committee Name to filter:");
	                    String campCommitteeName = scanner.nextLine();
	                	
	                	TreeMap<String, ReportModel> filterCCNameModel = new FilterReportController(reportModel).filterByCampCommitteeName(campCommitteeName);
	                	reportModel = filterCCNameModel;//new HashMap<>(filterCCNameModel);
	                    
	                    break;
	                }
	                case 3 -> {
	                	System.out.println("Enter Attendee Name to filter:");
	                    String attendeeName = scanner.nextLine();
	                	
	                	TreeMap<String, ReportModel> filterANameModel = new FilterReportController(reportModel).filterByAttendeeName(attendeeName);
	                	reportModel = filterANameModel;//new HashMap<>(filterANameModel);
	                    
	                    break;
	                }
	                case 4 -> {
	                    
	                    break;
	                }
	               default -> {
	            	   reportModel = null;
	               }
                }   
                
                if (reportModel.isEmpty()) {
                    System.out.println("No camps to generate a report for.");
                    return;
                }
                
                FileWriter fileWriter = new FileWriter(filePath); //WILL GIVE ERROR IF PATH DOES NOT EXIST

                // Create a BufferedWriter to efficiently write to the file
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                
                // Write text to the file
                bufferedWriter.write("Camp(s) by : " + reportModel.get(reportModel.keySet().toArray()[0]).getStaffInChargeID());
                bufferedWriter.newLine();
                bufferedWriter.write("================================================");
                bufferedWriter.newLine();

                for(String campName : reportModel.keySet()){
                    bufferedWriter.write("Camp Name : " + campName);
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Start Date : " + reportModel.get(campName).getStartDate());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp End Date : " + reportModel.get(campName).getEndDate());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Registration End Date : " + reportModel.get(campName).getRegistrationEndDate());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Location : " + reportModel.get(campName).getLocation());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Accessbility : " + reportModel.get(campName).getCampAccessbility());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Total Slots : " + reportModel.get(campName).getTotalSlots());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Committee Slots : " + reportModel.get(campName).getCampCommitteeSlots());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Description : " + reportModel.get(campName).getDescription());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Staff In Charge ID : " + reportModel.get(campName).getStaffInChargeID());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Visbility : " + reportModel.get(campName).isVisbility());
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                    bufferedWriter.write("Camp Blacklist : ");
                    bufferedWriter.newLine();
                    
                    ArrayList<String> blacklist = reportModel.get(campName).getBlacklistName();
                    
                    if(blacklist != null) {
                    	Collections.sort(blacklist); //sort in alphabetical order
	                    for (String blacklistMember : blacklist) {
	                        if (blacklistMember != null) {
	                            bufferedWriter.write(blacklistMember);
	                            bufferedWriter.newLine();  
	                        }
	                    }
                    }

                    bufferedWriter.newLine();
                    
                    Collections.sort(reportModel.get(campName).getCampCommitteeName());
                    bufferedWriter.write("Camp Committee Members: ");
                    bufferedWriter.newLine();
                    for (String committeeMember : reportModel.get(campName).getCampCommitteeName()) {
                        if (committeeMember != null) {
                            bufferedWriter.write(committeeMember);
                            bufferedWriter.newLine();
                        }
                    }

                    Collections.sort(reportModel.get(campName).getParticipantsName());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Attendees: ");
                    bufferedWriter.newLine();
                    for (String committeeMember : reportModel.get(campName).getParticipantsName()) {
                        if (committeeMember != null) {
                            bufferedWriter.write(committeeMember);
                            bufferedWriter.newLine();
                        }
                    }
                    bufferedWriter.newLine();                    
                    bufferedWriter.write("================================================");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
                System.out.println("Data has been written to the file successfully.");	
                
            } catch (Exception e){
                System.out.println("Invalid input, please try again");
                scanner.nextLine();
            }
            break;
        }
    }
}
