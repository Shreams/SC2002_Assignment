package org.example.camps.controllers;

import org.example.camps.interfaces.ICampController;
import org.example.camps.interfaces.ICampView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// Act as a controller for the Student class to talk to camp controllers
public class CampStudentController implements ICampController {

    private ICampView view;
    private CampController campController;

    public CampStudentController(CampController campController) {
        this.campController = campController;
    }

    @Override
    public void render(ICampView view) {
        this.view = view;
        view.display();
    }



    // ####################
    // #  Camp Modifiers  #
    // ####################

    public boolean withdrawFromCamp(String campID, String userID){
        // By withdrawing
        // Need to check if user is part of the camp and is not a camp committee

        if(isStudentAlreadyPartOfCampCommittee(campID, userID)){
            System.out.println("Unable to withdraw from camp as you are part of the camp committee");
            return false;
        }else if(!isStudentAlreadyPartOfCamp(campID, userID)) {
            System.out.println("Unable to withdraw from camp as you are not part of the camp");
            return false;
        }


        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                System.out.print("Confirm to withdraw from camp? (Y/N)");
                String confirm = scanner.next();

                if (confirm.equals("Y") || confirm.equals("y")) {
                    System.out.println("Successfully withdrawn from camp");
                    campController.removeParticipant(campID, userID);
                    return true;
                } else if (confirm.equals("N") || confirm.equals("n"))
                    System.out.println("Withdrawal cancelled");
                    return false;

            }catch (Exception e){
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

    public boolean joinCamp(String userID, String campName, String facultyName, boolean isCampCommittee, Date currentDate){
        // Need check if camp is full
        // need check if student is blacklisted
        // need check if student is already part of the camp committee of another camp
        // If they join as committee, make sure that it does not exceed the total number of committee slots
        // need check for overlapping dates
        // need check if student is already part of the camp
        // need check if student is already part of the camp committee
        // need compare if registration end date is before current login date

        // If all pass, then add student to the camp
        if(campController.isCampFull(campName)) {
            System.out.println("Unable to join as camp is full");

        } else if(isStudentBlacklisted(campName, userID)) {
            System.out.println("Unable to join camp as you are blacklisted");

        } else if(isStudentPastRegistrationDate(campName, currentDate)){
            System.out.println("Unable to join camp as registration date has passed");

        } else if(willStudentCampDatesClash(campName)){
            System.out.println("Unable to join camp as camp dates will clash with another camp");

        } else if(isStudentAlreadyPartOfCamp(campName, userID)){
            System.out.println("Unable to join camp as you are already part of the camp");

        } else if(isStudentAlreadyPartOfCampCommittee(campName, userID)){
            System.out.println("Unable to join camp as you are already part of the camp committee");

        } else if(isStudentAlreadyPartOfCampCommitteeOfAnotherCamp(userID)){
            System.out.println("Unable to join camp as you are already part of the camp committee of another camp");

        } else {
            if(isCampCommittee){
                if(isCampCommitteeFull(campName)){
                    System.out.println("Unable to join camp as the camp committee is full");
                }else{
                    campController.addCampCommittee(campName, userID);
                    System.out.println("Successfully joined camp as camp committee");
                    return true;
                }
            }else {
                campController.addParticipant(campName, userID);
                System.out.println("Successfully joined as camp participant");
                return true;
            }

        }

        return false;

    }



    // ################
    // #  Validators  #
    // ################

    public boolean hasAnyCampsToView(String userID, String facultyName){
        return campController.hasAnyCampsToView(userID, facultyName);
    }

    private boolean willStudentCampDatesClash(String campName){
        return campController.isCampDateOverlapping(campName);
    }

    private boolean isStudentPastRegistrationDate(String campName, Date currentDate){
        Date registrationEndDate = campController.getCampRegistrationEndDate(campName);

        return currentDate.after(registrationEndDate);
    }

    public boolean isStudentAlreadyPartOfCampCommittee(String campName, String studentID){
        ArrayList<String> campCommittee = campController.getCampCommittee(campName);

        return campCommittee.contains(studentID);
    }

    public boolean isStudentAlreadyPartOfCampCommitteeOfAnotherCamp(String studentID){
        return campController.isAlreadyPartOfCampCommitteeOfAnotherCamp(studentID);
    }

    private boolean isCampCommitteeFull(String campName){
        int numOfCommittee = campController.getCampCommittee(campName).size();
        int numOfCommitteeSlots = campController.getCampCommitteeSlots(campName);

        return numOfCommittee >= numOfCommitteeSlots;
    }

    private boolean isStudentAlreadyPartOfCamp(String campName, String studentID){
        ArrayList<String> students = campController.getCampParticipants(campName);

        return students.contains(studentID);
    }

    private boolean isStudentBlacklisted(String campID, String studentID){
        ArrayList<String> blackList = campController.getCampBlacklist(campID);

        return blackList.contains(studentID);
    }

    // ####################
    // #    Views         #
    // ####################

    @Override
    public ArrayList<String> viewCampsByFilter(int filterChoice, String userID, String facultyName, boolean viewAll) {
        // location, dates, camp names

        Scanner scanner = new Scanner(System.in);
//        System.out.println("The list is sorted in alphabetical order of camp names");
//        System.out.println("The same char, the shorter string first");
//        System.out.println("UpperCase first then smaller case");
        System.out.println();
        // no filter
        if (campController.isAllCampsEmpty()) {
            System.out.println("No camps created yet");
            return null;
        }

        if (filterChoice == 1) {

            // need change
            if (viewAll)
            {
                return campController.viewAllCamps(userID, true, facultyName);
            }
            else
            {
                return campController.viewMyCamps(userID, true, facultyName);
            }
        }
        // location
        else if (filterChoice == 2) {

            while (true) {
                try {
                    String findLocation;
                    System.out.println("Enter location name (case not sensitive): ");
                    findLocation = scanner.nextLine();
                    return campController.viewCampsFilterByLocation(userID, true, facultyName, findLocation, viewAll);

                } catch (Exception e) {
                    System.out.println("Invalid input");

                }
            }

        }
        // date
        else if (filterChoice == 3 || filterChoice == 4) {
            while (true) {
                // Prompt the user to input a date in the format "ddmmyy"
                System.out.print("Enter a date (dd/mm/yyyy): ");
                String userInput = scanner.nextLine();

                // Define the date format
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false); // Disable lenient parsing to enforce strict date validation

                try {
                    // Parse the user input into a Date object
                    Date parsedDate = dateFormat.parse(userInput);

//					// Display the parsed Date object
//					System.out.println("Parsed Date: " + parsedDate);
                    if (filterChoice == 3) {
                        return campController.viewCampsFilterByStartDate(userID, true, facultyName, parsedDate, viewAll);
                    } else {
                        return campController.viewCampsFilterByRegDate(userID, true, facultyName, parsedDate, viewAll);
                    }
//					break;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format dd/mm/yyyy.");

                }
            }
        }
        else if(filterChoice == 5){ // Camp name
            while (true) {
                try {
                    String campName;
                    System.out.println("Enter camp name (case not sensitive): ");
                    campName = scanner.nextLine();
                    return campController.viewCampsFilterByCampName(userID, true, facultyName, campName, viewAll);

                } catch (Exception e) {
                    System.out.println("Invalid input");
                    scanner.nextLine();

                }
            }
        }

        return null;

//
    }


    @Override
    public ArrayList<String> viewAllCamps(String userID, String facultyName) {
        return campController.viewAllCamps(userID, true, facultyName);
    }


    @Override
    public void viewMyCamps(String userID, String facultyName) {
        // For student is all the camps they have joined
        campController.viewMyCamps(userID, true, facultyName);
    }


    // ########################
    // #  Accessor & Mutator  #
    // ########################

    @Override
    public void getCampDetails(String campName) {
        campController.showCampDetails(campName);
    }

    @Override
    public String getCampName(int idx, String userID){
        return campController.getCampName(idx, userID, true);
    }

    public boolean hasAnyCamps(String userID, String facultyName){
        return campController.hasAnyCamps(userID, true, facultyName);
    }

    @Override
    public ArrayList<String> getCampNames(String userID, String facultyName){
        return campController.getCampsNames(userID, true, facultyName);
    }

    public int getNumOfCampsToView(String userID, String facultyName){
        return campController.getNumOfCampsToView(userID, facultyName);
    }

    public String getCampNameToView(int idx, String userID, String facultyName){
        return campController.getCampNameToView(idx, userID, facultyName);
    }

    public String getCampNameOfCampCommittee(String studentID){
        // Check if student is part of camp committee of another camp
        if (!isStudentAlreadyPartOfCampCommitteeOfAnotherCamp(studentID))
            return null;

        return campController.getCampNameOfCampCommittee(studentID);
    }

    public void addStudentToBlacklist(String campID, String studentID){
        campController.addStudentToBlacklist(campID, studentID);
    }

    public ArrayList<String> getParticipantsInCamp(String campID) {
        return campController.getCampParticipants(campID);
    }

    public ArrayList<String> getCampCommitteeInCamp(String campID) {
        return campController.getCampCommittee(campID);
    }

    public ArrayList<String> getBlacklistInCamp(String campID) {
        return campController.getCampBlacklist(campID);
    }


}
