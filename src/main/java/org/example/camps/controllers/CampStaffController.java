package org.example.camps.controllers;


import org.example.camps.interfaces.ICampController;
import org.example.camps.interfaces.ICampView;
import org.example.camps.views.CreateCampView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// Act as a controller for the Staff class to talk to camp controllers
public class CampStaffController implements ICampController {

    private ICampView view;

    private CampController campController;


    public CampStaffController(CampController campController) {
        this.campController = campController;
    }


    // ################
    // #  Validators  #
    // ################

    public boolean isCampsCreated(){
        return campController.isAllCampsEmpty();
    }


    // Return a boolan if there is any camp created by the staff
    public boolean hasAnyCamps (String userID){
        return campController.hasAnyCamps(userID, false, "");
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
                return campController.viewAllCamps(userID, false, facultyName);
            }
            else
            {
                return campController.viewMyCamps(userID, false, facultyName);
            }
        }
        // location
        else if (filterChoice == 2) {

            while (true) {
                try {
                    String findLocation;
                    System.out.println("Enter location name (case not sensitive): ");
                    findLocation = scanner.nextLine();
                    return campController.viewCampsFilterByLocation(userID, false, facultyName, findLocation, viewAll);


                } catch (Exception e) {
                    System.out.println("Invalid input");

                }
            }

        }
        // date
        else if (filterChoice == 3 || filterChoice == 4) {
            while (true) {
                // Prompt the user to input a date in the format "ddmmyy"
                System.out.print("Enter a date (dd/MM/yyyy): ");
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
                        return campController.viewCampsFilterByStartDate(userID, false, facultyName, parsedDate,viewAll);
                    } else {
                        return campController.viewCampsFilterByRegDate(userID, false, facultyName, parsedDate, viewAll);
                    }

                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format dd/MM/yyyy.");

                }
            }
        }
        else if(filterChoice == 5){ // Camp name
            while (true) {
                try {
                    String campName;
                    System.out.println("Enter camp name (case not sensitive): ");
                    campName = scanner.nextLine();
                    return campController.viewCampsFilterByCampName(userID, false, facultyName, campName, viewAll);

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
    public void render(ICampView view) {
        this.view = view;
        view.display();
    }

    @Override
    public ArrayList<String> viewAllCamps (String userID, String facultyName){
        return campController.viewAllCamps(userID, false, facultyName);
    }

    @Override
    public void viewMyCamps (String userID, String facultyName){
        // For staff is all the camps they created
        campController.viewMyCamps(userID, false, facultyName);
    }


    // ####################
    // #  Camp Modifiers  #
    // ####################

    public String createCamp(String userID, String facultyName) {

        String campName = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                // Ask all the detail here
                render(new CreateCampView());


                while (true) {
                    try {

                        System.out.print("Enter camp name: ");
                        campName = sc.nextLine();

                        if (campController.getIsCampNameTaken(campName)) {
                            throw new Exception("Camp name is taken");
                        }

                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input or camp name is taken");
                        sc.nextLine();
                    }
                }


                System.out.print("Enter camp description: ");
                String description = sc.nextLine();

                System.out.print("Enter camp location: ");
                String location = sc.nextLine();

                System.out.println("Select camp accessibility: ");
                System.out.println("1. NTU");
                System.out.println("2. " + facultyName);
                int choice = sc.nextInt();
                String accessibility = "";

                while (true) {
                    try {
                        switch (choice) {
                            case 1 -> {
                                accessibility = "NTU";
                            }
                            case 2 -> {
                                accessibility = facultyName;
                            }
                            default -> {
                                throw new Exception("Invalid choice");
                            }
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid choice");
                    }
                }

                sc.nextLine();
                String date;
                Date[] dates = new Date[2];
                while (true) {
                    try {
                        System.out.print("Enter camp start date (dd/mm/yyyy): ");
                        date = sc.nextLine();
                        dates[0] = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid date format");
                    }
                }

                while (true) {
                    try {
                        System.out.print("Enter camp end date (dd/mm/yyyy): ");
                        date = sc.nextLine();
                        dates[1] = new SimpleDateFormat("dd/MM/yyyy").parse(date);

                        if (dates[1].before(dates[0])) {
                            throw new Exception("End date cannot be before start date");
                        }

                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid date or end date is before start date");
                    }
                }

                //Registration end date
                Date registrationEndDate = new Date();
                while (true) {
                    try {
                        System.out.print("Enter registration end date (dd/mm/yyyy): ");
                        date = sc.nextLine();
                        registrationEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);

                        if (registrationEndDate.after(dates[0])) {
                            throw new Exception("Registration end date cannot be after camp start date");
                        }

                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid date or registration end date is after camp start date");
                    }
                }


                System.out.println("Select visibility: ");
                System.out.println("1. ON");
                System.out.println("2. OFF");
                choice = sc.nextInt();
                boolean visibility = false;

                while (true) {
                    try {
                        switch (choice) {
                            case 1 -> {
                                visibility = true;
                            }
                            case 2 -> {
                            }
                            default -> {
                                throw new Exception("Invalid choice");
                            }
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid choice");
                    }
                }



                int totalSlots;
                while(true){
                    try {

                        System.out.print("Enter total slots: ");
                        totalSlots = sc.nextInt();

                        if(totalSlots < 0){
                            throw new Exception("Total slots cannot be negative");
                        }
                        break;

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                }


                int campCommitteeSlots;
                while (true) {
                    try {


                        System.out.print("Enter camp committee slots: ");
                        campCommitteeSlots = sc.nextInt();


                        if (totalSlots < campCommitteeSlots) {
                            throw new Exception("New camp committee slots cannot be lesser than total slots assigned");
                        }

                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                campController.addCamp(campName, dates, registrationEndDate, location, accessibility, totalSlots, campCommitteeSlots, description, userID, visibility);
                break;
            } catch(Exception e){
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }

        return campName;
    }

    public void deleteCamp(String userID, String campID) {
        campController.deleteCamp(userID, campID);
    }

    public void editCamp(String userID, String campID, String facultyName) {
        // Ask all the detail here
        Scanner sc = new Scanner(System.in);

        int choice;
        String date;
        Date[] dates = campController.getCampDates(campID);

        // Check if the camp has any participants and camp committee
        if (!(campController.getCampParticipants(campID).isEmpty() && campController.getCampCommittee(campID).isEmpty())){
            System.out.println("There are participants in this camp, cannot edit");
        }else{
            while (true) {
                try {
                    System.out.println("Select an option to edit: ");
                    System.out.println("1. Camp Description");
                    System.out.println("2. Camp Location");
                    System.out.println("3. Camp Accessibility");
                    System.out.println("4. Camp Start Date");
                    System.out.println("5. Camp End Date");
                    System.out.println("6. Registration End Date");
                    System.out.println("7. Camp Visibility");
                    System.out.println("8. Total Slots");
                    System.out.println("9. Camp Committee Slots");
                    System.out.println("10. Back");
                    choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter new camp description: ");
                            String description = sc.nextLine();
                            campController.setCampDescription(campID, description);
                            System.out.println("Camp description changed successfully");
                        }
                        case 2 -> {
                            System.out.print("Enter new camp location: ");
                            String location = sc.nextLine();
                            campController.setLocation(campID, location);
                            System.out.println("Camp location changed successfully");
                        }
                        case 3 -> {
                            System.out.println("Select new camp accessibility: ");
                            System.out.println("1. NTU");
                            System.out.println("2. " + facultyName);
                            int choice2 = sc.nextInt();
                            String accessibility = "";

                            while (true) {
                                try {
                                    switch (choice2) {
                                        case 1 -> {
                                            accessibility = "NTU";
                                        }
                                        case 2 -> {
                                            accessibility = facultyName;
                                        }
                                        default -> {
                                            throw new Exception("Invalid choice");
                                        }
                                    }
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid choice");
                                }
                            }
                            campController.setCampAccessibility(campID, accessibility);
                            System.out.println("Camp accessibility changed successfully");
                        }
                        case 4 -> {
                            while (true) {
                                try {
                                    System.out.print("Enter new camp start date (dd/mm/yyyy): ");
                                    date = sc.nextLine();
                                    dates[0] = new SimpleDateFormat("dd/MM/yyyy").parse(date);


                                    // Check start date not before registration end date
                                    if(dates[0].before(campController.getCampRegistrationEndDate(campID))){
                                        throw new Exception("Start date cannot be before registration end date");
                                    }

                                    // If new start date is after end date then they must edit end date too
                                    if (dates[0].after(dates[1])) {
                                        while (true) {
                                            try {
                                                System.out.print
                                                        ("Enter new camp end date (dd/mm/yyyy): ");
                                                date = sc.nextLine();
                                                dates[1] = new SimpleDateFormat("dd/MM/yyyy").parse(date);

                                                if (dates[1].before(dates[0])) {
                                                    throw new Exception("End date cannot be before start date");
                                                }

                                                break;
                                            } catch (Exception e) {
                                                System.out.println("Invalid date or end date is before start date");
                                            }
                                        }
                                        campController.setEndDate(campID, dates[1]);
                                        System.out.println("Camp end date changed successfully");
                                    }


                                    break;

                                } catch (Exception e) {
                                    System.out.println("Invalid date format");
                                }
                            }
                            campController.setStartDate(campID, dates[0]);
                            System.out.println("Camp start date changed successfully");
                        }
                        case 5 -> {
                            while (true) {
                                try {
                                    System.out.print
                                            ("Enter new camp end date (dd/mm/yyyy): ");
                                    date = sc.nextLine();
                                    dates[1] = new SimpleDateFormat("dd/MM/yyyy").parse(date);

                                    if (dates[1].before(dates[0])) {
                                        throw new Exception("End date cannot be before start date");
                                    }

                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid date or end date is before start date");
                                }
                            }
                            campController.setEndDate(campID, dates[1]);
                            System.out.println("Camp end date changed successfully");
                        }

                        case 6 -> {
                            Date registrationEndDate = new Date();
                            while (true) {
                                try {
                                    System.out.print
                                            ("Enter new registration end date (dd/mm/yyyy): ");
                                    date = sc.nextLine();
                                    registrationEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);

                                    if (registrationEndDate.after(dates[0])) {
                                        throw new Exception("Registration end date cannot be after camp start date");
                                    }

                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid date or registration end date is after camp start date");
                                }
                            }
                            campController.setRegistrationEndDate(campID, registrationEndDate);
                            System.out.println("Registration end date changed successfully");
                        }

                        case 7 -> {

                            if (!campController.getCampParticipants(campID).isEmpty() || !campController.getCampCommittee(campID).isEmpty()) {
                                System.out.println("There are participants in this camp, cannot change visibility");
                            } else {

                                boolean visibility = campController.getCampVisibility(campID);

                                while (true) {
                                    try {
                                        System.out.println("Select new visibility: ");
                                        System.out.println("1. ON");
                                        System.out.println("2. OFF");
                                        int choice2 = sc.nextInt();

                                        switch (choice2) {
                                            case 1 -> {
                                                visibility = true;
                                            }
                                            case 2 -> {
                                                visibility = false;
                                            }
                                            default -> {
                                                throw new Exception("Invalid choice");
                                            }
                                        }
                                        break;
                                    } catch (Exception e) {
                                        System.out.println("Invalid choice");
                                    }
                                }
                                campController.setCampVisibility(campID, visibility);
                                System.out.println("Camp visibility changed successfully");
                            }


                        }

                        case 8 -> {
                            // New slot cannot be lesser than current number of participants + camp committee slots
                            int newTotalSlots;
                            while (true) {
                                try {
                                    int totalSlots = campController.getCampTotalSlots(campID);
                                    int currentParticipants = campController.getCampParticipants(campID).size();
                                    int currentCampCommittee = campController.getCampCommittee(campID).size();

                                    System.out.print("Enter new total slots: ");
                                    newTotalSlots = sc.nextInt();


                                    if (newTotalSlots < currentParticipants + currentCampCommittee) {
                                        throw new Exception("New total slots cannot be lesser than current number of participants + camp committee slots");
                                    }



                                    break;
                                } catch (Exception e) {
                                    System.out.println("New total slots cannot be lesser than current number of participants + camp committee slots");
                                }
                            }
                            campController.setCampTotalSlots(campID, newTotalSlots);
                            System.out.println("Total slots changed successfully");
                        }

                        case 9 -> {

                            // New slot cannot be lesser than current number of camp committee and
                            // neither can the (new slots + total participants) more than total slots
                            int newCampCommitteeSlots;
                            while (true) {
                                try {
                                    int totalSlots = campController.getCampTotalSlots(campID);
                                    int currentCampCommittee = campController.getCampCommitteeSlots(campID);
                                    int currentParticipants = campController.getCampParticipants(campID).size();

                                    System.out.print("Enter new camp committee slots: ");
                                    newCampCommitteeSlots = sc.nextInt();

                                    if (newCampCommitteeSlots < currentCampCommittee) {
                                        throw new Exception("New camp committee slots cannot be lesser than current number of camp committee");
                                    } else if ((newCampCommitteeSlots + currentParticipants) > totalSlots) {
                                        throw new Exception("New camp committee slots + total participants cannot be more than total slots");
                                    }
                                    break;
                                } catch (Exception e) {
                                    System.out.println("New camp committee slots cannot be lesser than current number of camp committee or new camp committee slots + total participants cannot be more than total slots");
                                }
                            }
                            campController.setCampCommitteeSlots(campID, newCampCommitteeSlots);
                            System.out.println("Camp committee slots changed successfully");

                        }

                        case 10 -> {
                            return;
                        }
                    }

                    break;
                } catch (Exception e) {
                    System.out.println("Invalid choice");
                }
            }
        }


    }


    // ########################
    // #  Accessor & Mutator  #
    // ########################

    public String getStaffInChargeIDOfCamp(String campID) {
        return campController.getStaffInChargeID(campID);
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

    public ArrayList<Object> getCampDetailsForReport(String campName) {
        return campController.getCampDetailsForReport(campName);
    }

    public ArrayList<String> getAllCampIDsUnderStaff(String userID) {
        return campController.getAllCampIDsUnderStaff(userID);
    }

    public ArrayList<String> getCampNames (String userID, String facultyName){
        return campController.getCampsNames(userID, false, facultyName);
    }

    @Override
    public String getCampName(int idx, String userID) {
        return campController.getCampName(idx, userID, false);
    }

    @Override
    public void getCampDetails(String campName) {
        campController.showCampDetails(campName);
    }

}


