package org.example.reports.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.example.filters.controllers.FilterReportController;
import org.example.reports.models.ReportModel;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Group1
 * @version 1.0
 * 
 * The `GenerateCsv` class is responsible for generating CSV reports based on camp committee data and general reports.
 * It implements the `IReport` interface, providing methods for generating camp committee reports and general reports.
 * These reports are created using the Apache POI library for Excel manipulation.
 */
public class GenerateCsv extends GenerateReport {
	
	
	/**
     * Generates a CSV report for camp committee members.
     *
     * @param reportModel A TreeMap containing camp committee report data.
     * @param points      A HashMap containing points data for committee members.
     */
	@Override
	public void generateCampCommitteeReport(TreeMap<String, ReportModel> reportModel, HashMap<String, Integer> points) {
		System.out.println("Generating Csv report for Camp Committee...");

		int pointsindex = 0;
	    Scanner scanner = new Scanner(System.in);
	    while (true) {
	        try {
	            String currentDirectory = System.getProperty("user.dir");
	            String relativeFilePath = "Reports_gen\\camp_committee_report.csv";
	            String filePath = currentDirectory + "/" + relativeFilePath;

	            // Ensure that the directory exists
	            File directory = new File(filePath).getParentFile();
	            if (!directory.exists()) {
	                // create it if it doesn't exist
	                if (directory.mkdirs()) {
	                    System.out.println("Directory created successfully.");
	                } else {
	                    System.out.println("Failed to create directory.");
	                    return; // exit if it fails
	                }
	            }
	            
	            if (reportModel.isEmpty()) {
                    System.out.println("No camps to generate a report for.");
                    return;
                }

	            // Create a new Excel workbook
	            Workbook workbook = new XSSFWorkbook();

	            for (String campName : reportModel.keySet()) {
	                Sheet sheet = workbook.createSheet(campName);
	                Row headerRow = sheet.createRow(0);

	                headerRow.createCell(0).setCellValue("Camp Committee Members");

	                int rowIndex = 1;

	                ArrayList<String> campCommitteeMembers = reportModel.get(campName).getCampCommitteeName();
	                if (campCommitteeMembers != null && !campCommitteeMembers.isEmpty()) {
	                    Collections.sort(campCommitteeMembers);

	                    for (String committeeMember : campCommitteeMembers) {
	                        Row row = sheet.createRow(rowIndex++);
	                        row.createCell(0).setCellValue(committeeMember);
	                        row.createCell(1).setCellValue(points.get(committeeMember));
	                    }
	                }
	            }

	            // Auto-size the column width
	            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
	                Sheet sheet = workbook.getSheetAt(i);
	                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
	                    sheet.autoSizeColumn(j);
	                }
	            }

	            // Write the workbook content to a file
	            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	                workbook.write(fileOut);
	            }

	            System.out.println("Camp Committee report has been written to the Csv file successfully.");
	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	            e.printStackTrace();
	        }
	        break;
	    }
	}
	
	/**
     * Generates a general CSV report based on various filtering criteria.
     *
     * @param reportModel A TreeMap containing general report data.
     */
    @Override
    public void generateReport(TreeMap<String, ReportModel> reportModel) {
        System.out.println("Generating CSV report...");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                String currentDirectory = System.getProperty("user.dir");
                String relativeFilePath = "Reports_gen\\report.csv";
                String filePath = currentDirectory + "/" + relativeFilePath;
                
                // Ensure that the directory exists
                File directory = new File(filePath).getParentFile();
                if (!directory.exists()) {
                    // create it if it doesn't exist (i will crash if it does not exist)
                    if (directory.mkdirs()) {
                        System.out.println("Directory created successfully.");
                    } else {
                        System.out.println("Failed to create directory.");
                        return; // exit if it fails
                    }
                }

                System.out.println("How do you want to filter by?");
                System.out.println("1. Camp Name");    //Search by Camp Name
                System.out.println("2. Camp Committee"); //Search by Camp Committee Name
                System.out.println("3. Participants");  //Search by Attendee Name
                System.out.println("4. View All Under you");      //Show All
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
                
                Workbook workbook = new XSSFWorkbook();
                
                int check1 = 0;
                int check2 = 0;

                for (String campName : reportModel.keySet()){
                	
                	
                	
                	Sheet sheet = workbook.createSheet(campName);
                    Row headerRow = sheet.createRow(0);
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    int startRow = 0;
                    int endRow = 0;
                    int rowIndex = 1;
                    
                    headerRow.createCell(0).setCellValue("Camp Name");                    
                    headerRow.createCell(1).setCellValue("Camp Committee Members");
                	headerRow.createCell(2).setCellValue("Attendees");
                    headerRow.createCell(3).setCellValue("Blacklist");
                    headerRow.createCell(4).setCellValue("Start Date");
                    headerRow.createCell(5).setCellValue("End Date");
                    headerRow.createCell(6).setCellValue("Registration Date");
                    headerRow.createCell(7).setCellValue("Location");
                    headerRow.createCell(8).setCellValue("Camp Accessbility");
                    headerRow.createCell(9).setCellValue("Total Slots");
                    headerRow.createCell(10).setCellValue("Camp Committee S	lots");
                    headerRow.createCell(11).setCellValue("Description");
                    headerRow.createCell(12).setCellValue("Staff In Charge ID");
                    headerRow.createCell(13).setCellValue("Visibility");
                    
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(campName);
                    //row.createCell(1).setCellValue(CampCommiteeData); //this will be filled later
                    //row.createCell(2).setCellValue(Participantdata); //this will be filled later
                    
                    ArrayList<String> blacklist = reportModel.get(campName).getBlacklistName();
                    if (blacklist != null) {
                        Collections.sort(blacklist); // sort in alphabetical order
                        String blacklistString = String.join(", ", blacklist);
                        row.createCell(3).setCellValue(blacklistString);
                    }
                    
                    row.createCell(4).setCellValue(dateFormat.format(reportModel.get(campName).getStartDate()));
                    row.createCell(5).setCellValue(dateFormat.format(reportModel.get(campName).getEndDate()));
                    row.createCell(6).setCellValue(dateFormat.format(reportModel.get(campName).getRegistrationEndDate()));
                    row.createCell(7).setCellValue(reportModel.get(campName).getLocation());
                    row.createCell(8).setCellValue(reportModel.get(campName).getCampAccessbility());
                    row.createCell(9).setCellValue(reportModel.get(campName).getTotalSlots());
                    row.createCell(10).setCellValue(reportModel.get(campName).getCampCommitteeSlots());
                    row.createCell(11).setCellValue(reportModel.get(campName).getDescription());
                    row.createCell(12).setCellValue(reportModel.get(campName).getStaffInChargeID());
                    row.createCell(13).setCellValue(reportModel.get(campName).isVisbility());
                    
                    Collections.sort(reportModel.get(campName).getCampCommitteeName());
                	Collections.sort(reportModel.get(campName).getParticipantsName());
                	
                	int committeeListSize = reportModel.get(campName).getCampCommitteeName().size();
                	int studentListSize = reportModel.get(campName).getParticipantsName().size();
                	
                	for(int j = 0; j < Math.max(committeeListSize, studentListSize); j++) {
                		
                		String CCName = null;
                	    String PName = null;
                		if (j < committeeListSize) {
                	        CCName = reportModel.get(campName).getCampCommitteeName().get(j);
                	    }
                	    if (j < studentListSize) {
                	        PName = reportModel.get(campName).getParticipantsName().get(j);
                	    }
                    	if (j < committeeListSize && CCName != null || j < studentListSize && PName != null) {
                    		if(check1 == 0) {
                    			row.createCell(1).setCellValue(CCName);
                    			row.createCell(2).setCellValue(PName);
                    			check1 = 1;
                    		}
                    		else{
                    			Row row1 = sheet.createRow(rowIndex++);
                    			row1.createCell(1).setCellValue(CCName);
                    			row1.createCell(2).setCellValue(PName);
                    		}
                    	}
                    }

                    // Auto-size the column width
                    for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                   	 	sheet.autoSizeColumn(i);
                    }
                    
                    int firstRow = 1;  
                    int lastRow = rowIndex-1;  
                    
                    if (lastRow > firstRow) { // Check if there are two or more rows to merge
	                    for (int j = 0; j <= 13; j++) {
	                    	if(j == 1 || j == 2) continue;
	                    	sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, j, j));
	                    }
                    }
                    check1 = 0;
                    check2 = 0;
                }
    
                // Write the workbook content to a file
                String relativeExcelFilePath = "Reports_gen\\report.csv";
                String excelFilePath = currentDirectory + "/" + relativeExcelFilePath;
                try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                    workbook.write(fileOut);
                }

                System.out.println("Data has been written to the Csv file successfully.");

            } catch (Exception e){
            	System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
            break;
        }
    }
}
