package org.example.camps.controllers;

import org.example.camps.interfaces.ICampControllerView;
import org.example.camps.interfaces.ICampInfoGetters;
import org.example.camps.interfaces.ICampStudentControllerValidators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class manages interactions and operations related to a student's involvement in camps.
 * It interfaces with the {@link CampController} to modify camp-related information.
 *
 * @author Group1
 * @version 1.0
 */
public class CampStudentController implements ICampControllerView, ICampStudentControllerValidators, ICampInfoGetters {


    private CampController campController;


    /**
     * Constructs a CampStudentController object with a CampController.
     *
     * @param campController The CampController instance to interact with camp-related operations.
     */
    public CampStudentController(CampController campController) {
        this.campController = campController;
    }


    // ####################
    // #  Camp Modifiers  #
    // ####################

    /**
     * Allows a user to withdraw from a camp.
     *
     * @param campID The ID of the camp from which the user wants to withdraw.
     * @param userID The ID of the user who wants to withdraw from the camp.
     * @return True if the withdrawal is successful; otherwise, false.
     */
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

    /**
     * Allows a user to join a camp based on specified criteria.
     *
     * @param userID        The ID of the user attempting to join the camp.
     * @param campName      The name of the camp to join.
     * @param facultyName   The name of the faculty associated with the camp.
     * @param isCampCommittee Indicates if the user is joining as part of the camp committee.
     * @param currentDate   The current date when the join request is made.
     * @return True if the user successfully joins the camp; otherwise, false.
     */
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

        } else if(isCampDateOverlapping(campName, userID)){
            System.out.println("Unable to join camp as camp dates will clash with another camp");

        } else if(isStudentAlreadyPartOfCamp(campName, userID)){
            System.out.println("Unable to join camp as you are already part of the camp");

        } else if(isStudentAlreadyPartOfCampCommittee(campName, userID) && isCampCommittee){
            System.out.println("Unable to join camp as you are already part of the camp committee");

        } else if(isAlreadyPartOfCampCommitteeOfAnotherCamp(userID) && isCampCommittee){
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

    /**
     * Checks if a student has any camps available to view within a specific faculty.
     *
     * @param userID       The ID of the student.
     * @param facultyName  The name of the faculty to filter camps.
     * @return True if the student has any camps available to view within the specified faculty; otherwise, false.
     */
    public boolean hasAnyCampsToViewForStudents(String userID, String facultyName){
        return campController.hasAnyCampsToViewForStudents(userID, facultyName);
    }


    @Override
    public boolean isCampDateOverlapping(String campName, String userID){
        return campController.isCampDateOverlapping(campName, userID);
    }

    @Override
    public boolean isStudentPastRegistrationDate(String campName, Date currentDate){
        Date registrationEndDate = campController.getCampRegistrationEndDate(campName);

        return currentDate.after(registrationEndDate);
    }

    @Override
    public boolean isStudentAlreadyPartOfCampCommittee(String campName, String studentID){
        ArrayList<String> campCommittee = campController.getCampCommittee(campName);

        return campCommittee.contains(studentID);
    }


    @Override
    public boolean isAlreadyPartOfCampCommitteeOfAnotherCamp(String studentID){
        return campController.isAlreadyPartOfCampCommitteeOfAnotherCamp(studentID);
    }

    @Override
    public boolean isCampCommitteeFull(String campName){
        int numOfCommittee = campController.getCampCommittee(campName).size();
        int numOfCommitteeSlots = campController.getCampCommitteeSlots(campName);

        return numOfCommittee >= numOfCommitteeSlots;
    }

    @Override
    public boolean isStudentAlreadyPartOfCamp(String campName, String studentID){
        ArrayList<String> students = campController.getCampParticipants(campName);

        return students.contains(studentID);
    }

    @Override
    public boolean isStudentBlacklisted(String campID, String studentID){
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


//    @Override
//    public void viewMyCamps(String userID, String facultyName) {
//        // For student is all the camps they have joined
//        campController.viewMyCamps(userID, true, facultyName);
//    }


    // ########################
    // #  Accessor & Mutator  #
    // ########################

    /**
     * Retrieves the camp name associated with the camp committee of a student.
     *
     * @param studentID The ID of the student.
     * @return The camp name associated with the student's camp committee or null if not found.
     */
    public String getCampNameOfCampCommittee(String studentID){
        // Check if student is part of camp committee of another camp
        if (!isAlreadyPartOfCampCommitteeOfAnotherCamp(studentID))
            return null;

        return campController.getCampNameOfCampCommittee(studentID);
    }

    /**
     * Adds a student to the blacklist of a camp.
     *
     * @param campID    The ID of the camp.
     * @param studentID The ID of the student to be added to the blacklist.
     */
    public void addStudentToBlacklist(String campID, String studentID){
        campController.addStudentToBlacklist(campID, studentID);
    }


    @Override
    public void showCampDetails(String campName) {
        campController.showCampDetails(campName);
    }

    /**
     * Checks if there are any camps available for a specific user and faculty.
     *
     * @param userID      The user ID for checking camps.
     * @param facultyName The faculty name for filtering camps.
     * @return A boolean indicating if the user has any available camps within the specified faculty.
     */
    public boolean hasAnyCamps(String userID, String facultyName){
        return campController.hasAnyCamps(userID, true, facultyName);
    }

    @Override
    public ArrayList<String> getCampNames(String userID, String facultyName){
        return campController.getCampsNames(userID, true, facultyName);
    }


    @Override
    public ArrayList<String> getParticipantsInCamp(String campID) {
        return campController.getCampParticipants(campID);
    }

    @Override
    public ArrayList<String> getCampCommitteeInCamp(String campID) {
        return campController.getCampCommittee(campID);
    }

    @Override
    public ArrayList<String> getBlacklistInCamp(String campID) {
        return campController.getCampBlacklist(campID);
    }


}
