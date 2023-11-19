package org.example;


import org.example.appview.AppViewsController;
import org.example.appview.views.staff.*;
import org.example.appview.views.student.*;
import org.example.camps.controllers.CampController;
import org.example.camps.controllers.CampStaffController;
import org.example.camps.controllers.CampStudentController;
import org.example.filters.controllers.FilterViewController;
import org.example.query.enquiries.EnquiryController;
import org.example.query.enums.QueryStatus;
import org.example.query.suggestions.SuggestionController;
import org.example.reports.controllers.ReportController;
import org.example.reports.interfaces.IReport;
import org.example.reports.utils.GenerateCsv;
import org.example.reports.utils.GenerateTxt;
import org.example.users.controllers.StaffController;
import org.example.users.controllers.StudentController;
import org.example.users.models.StaffModel;
import org.example.users.models.User;
import org.example.users.models.StudentModel;
import org.example.extractexcel.ExtractExcelController;
import org.example.users.enums.UserType;
import org.example.extractexcel.ExtractExcelModel;
import org.example.extractexcel.ExtractExcelView;
import org.example.login.controllers.LoginController;
import org.example.users.views.StaffView;
import org.example.users.views.StudentView;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Database of users
        Scanner scanner = new Scanner(System.in);

        // Use to backtrack to previous page
        boolean back = false;

        // Grabbing the models and
        ArrayList<User> staffsModel = new ArrayList<User>();
        ArrayList<User> studentsModel = new ArrayList<User>();

        // Extract from excel
        ExtractExcelController extractExcelController = new ExtractExcelController(new ExtractExcelView());
        ArrayList<ExtractExcelModel> studentExcelModels = extractExcelController.getExcelData("src/main/java/org/example/resources/student_list.xlsx", UserType.STUDENT, 0,3,12);
        ArrayList<ExtractExcelModel> staffExcelModels = extractExcelController.getExcelData("src/main/java/org/example/resources/staff_list.xlsx", UserType.STAFF, 0,3, 6);


        // Add extracted data into the respective models
        for (ExtractExcelModel model : studentExcelModels) {
            studentsModel.add(new StudentModel(model.getName(), model.getUserID(), model.getFacultyName(),model.getEmail(), model.getUserType()));
        }

        for (ExtractExcelModel model : staffExcelModels) {
            staffsModel.add(new StaffModel(model.getName(), model.getUserID(), model.getFacultyName(),model.getEmail(), model.getUserType()));
        }

        // Print students details
//        for (ExtractExcelModel student : studentExcelModels) {
//            System.out.println();
//            System.out.println(student.getUserID());
//        }

        // Print staff details
//        for (ExtractExcelModel staff : staffExcelModels) {
//            System.out.println();
//            System.out.println(staff.getUserID());
//        }

        StaffController staffController = new StaffController(staffsModel, new StaffView());
        StudentController studentController = new StudentController(studentsModel, new StudentView());

        // There can only be 1 login user at a time
        LoginController loginController = new LoginController();

        // View that shows the user according to the domain
        AppViewsController appViewsController =  new AppViewsController();

        // Camp controllers
        CampController campController = new CampController();
        CampStaffController campStaffController = new CampStaffController(campController);
        CampStudentController campStudentController = new CampStudentController(campController);

        // Query controllers
        SuggestionController suggestionController = new SuggestionController();
        EnquiryController enquiryController = new EnquiryController();



        // Generate Dummy Camps for testing purposes during presentation
        campController.createDummyCamps(3);
        for (int i = 0; i < 3; i++) {
            staffController.addCampToList("UPAM", "DummyCamp"+i);
        }


        // ############ APP STARTS HERE ############
        while(true){

            int domain;
            boolean isLogin = false;
            String userID, password;

            //TODO: I want staffController and Student controller to be associated with loginController (uni directional) (So basically, i pass the controller as a param to loginController)

            while (true){
                try{
                    loginController.showLoginScreen();
                    domain = scanner.nextInt();
                    switch (domain) { // Will throw exception is user credentials does not match
                        case 1 -> // Student
                                loginController.login(studentController, UserType.STUDENT);
                        case 2 -> // Staff
                                loginController.login(staffController, UserType.STAFF);
                        default -> throw new Exception("Invalid domain");
                    }
                    isLogin = true;

                    // Ask user to change password if password is default
                    if(loginController.isUsingDefaultPassword()){

                        //Update the password in the respective model
                        switch (domain) {
                            case 1 -> loginController.setPassword(studentController);
                            case 2 -> loginController.setPassword(staffController);
                        }

                        System.out.println("Password changed successfully");
                    }
                    break;

                } catch (Exception e){
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                }
            }



            // Student domain
            while(isLogin && domain == 1){
                int pageSelection;
                try{
                    // Do all the other stuff here
                    appViewsController.showHomePage();
                    pageSelection = scanner.nextInt();

                    switch (pageSelection) {
                        case 1 -> {
                            // Accounts
                            while (true) {
                                try {
                                    appViewsController.showAccountViewPage();
                                    pageSelection = scanner.nextInt();
                                    switch (pageSelection) {
                                        case 1 -> { // Change password

                                            loginController.setPassword(studentController);
                                            System.out.println("Password changed successfully");
                                        }
                                        case 2 -> {
                                        } // Back

                                        default -> throw new Exception("Invalid input");
                                    }

                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    scanner.nextLine();
                                }
                            }
                        }

                        case 2 -> {
                            // Profile

                            // For student profile,
                            // They can see the camps they registered for + the roles they have

                            ArrayList<String> campRegistered= campStudentController.getCampNames(loginController.getUserID(),loginController.getFacultyName());
                            String committeeCamp="";
                            for (String camps : campRegistered)
                            {
                                //find which camp this person is a committee in
                                if(campStudentController.isStudentAlreadyPartOfCampCommittee(camps, loginController.getUserID())) {
                                    committeeCamp=camps;
                                }
                            }

                            studentController.getProfile(loginController.getUserID(),committeeCamp);
                        }

                        case 3 -> {
                            //Camps
                            back = false;
                            while(!back) {
                                try{
                                    appViewsController.appRender(new CampStudentMainCampMenu());
                                    pageSelection = scanner.nextInt();

                                    switch (pageSelection) {
                                        case 1 -> {
                                            // View All Camps  // TODO : FOR campStudentController & campStaffController, need to let them filter by . NEED to implement default view in alhphabetical order
                                            int choice;
                                            boolean innerBack = false;
                                            ArrayList<String> filterCampNames = new ArrayList<String>();

//                                            filterCampNames = campStudentController.viewAllCamps(loginController.getUserID(), loginController.getFacultyName()); // -> It will ask 1. View More details -> 1.join 2. Enquire 3. Suggest 4. back     2. back

                                            if(!campStudentController.hasAnyCampsToView(loginController.getUserID(), loginController.getFacultyName())){
                                                System.out.println("No camps to view");
                                            }
                                            else {
                                                while(true){
                                                    try {
                                                        innerBack = false;
                                                        System.out.println("*** View All Camps ***");

                                                        choice = new FilterViewController().menuSelectChoice();

                                                        if (choice == 6) // Back
                                                            break;


                                                        filterCampNames = campStudentController.viewCampsByFilter(choice,loginController.getUserID(), loginController.getFacultyName(), true);

                                                        if(filterCampNames == null){
                                                            continue;
                                                        }

                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input. Please enter a valid integer.");
                                                        scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
                                                    }

                                                    while(!innerBack){

                                                        try{
                                                            System.out.println("Select the camp index to view details (-1 to go back): ");
                                                            choice = scanner.nextInt();
                                                            if (choice == -1) {
                                                                innerBack = true;
                                                                break;
                                                            }

                                                            if (choice > filterCampNames.size() || choice < 1) {
                                                                throw new Exception("Invalid choice");
                                                            }

                                                        } catch (Exception e){
                                                            System.out.println("Invalid input");
                                                            scanner.nextLine();
//                                                        campStudentController.viewAllCamps(loginController.getUserID(), loginController.getFacultyName());
                                                            continue;
                                                        }

                                                        String campID = filterCampNames.get(choice-1); //campStudentController.getCampNameToView(choice, loginController.getUserID(), loginController.getFacultyName());
                                                        campStudentController.getCampDetails(campID);

                                                        boolean innerInnerBack = false;
                                                        while(!innerInnerBack){
                                                            try {
                                                                appViewsController.appRender(new CampStudentViewAllCampMenu());
                                                                pageSelection = scanner.nextInt();
                                                                switch (pageSelection) {
                                                                    case 1 -> { // Join Camp
                                                                        while(true){
                                                                            try{
                                                                                System.out.println("Select an option to join: ");
                                                                                System.out.println("1. Join as participant");
                                                                                System.out.println("2. Join as camp committee");
                                                                                System.out.println("3. Back");
                                                                                int joinChoice = scanner.nextInt();

                                                                                switch (joinChoice){
                                                                                    case 1 -> { // Join as participant
                                                                                        boolean valid = campStudentController.joinCamp(loginController.getUserID(), campID, loginController.getFacultyName(), false, loginController.getLoginDate());

                                                                                        if(valid){
                                                                                            studentController.addCampToList(loginController.getUserID(), campID);
                                                                                        }
                                                                                    }
                                                                                    case 2 -> { // Join as camp committee
                                                                                        boolean valid = campStudentController.joinCamp(loginController.getUserID(), campID, loginController.getFacultyName(), true, loginController.getLoginDate());

                                                                                        if(valid){
                                                                                            studentController.addCampToList(loginController.getUserID(), campID);
                                                                                            studentController.setIsCampCommittee(loginController.getUserID(), true);
                                                                                        }

                                                                                    }
                                                                                    case 3 -> {

                                                                                    }
                                                                                    default -> {
                                                                                        throw new Exception("Invalid input");
                                                                                    }
                                                                                }

                                                                                break;

                                                                            }catch (Exception e){
                                                                                System.out.println("Invalid input");
                                                                                scanner.nextLine();
                                                                            }
                                                                        }

                                                                    }
                                                                    case 2 -> {
                                                                        // Enquire
                                                                        // User cannot be a camp committee
                                                                        if(!campStudentController.isStudentAlreadyPartOfCampCommittee(campID, loginController.getUserID()))
                                                                            enquiryController.createQuery(loginController.getUserID(), campID);
                                                                        else
                                                                            System.out.println("You are a camp committee");
                                                                    }
                                                                    case 3 -> {
                                                                        // Suggest
                                                                        // Need to check if the user is a camp committee
                                                                        if(campStudentController.isStudentAlreadyPartOfCampCommittee(campID, loginController.getUserID())) {
                                                                            suggestionController.createQuery(loginController.getUserID(), campID);
                                                                            // Add points to the student
                                                                            studentController.addPoints(loginController.getUserID(), 1);
                                                                        }else
                                                                            System.out.println("You are not a camp committee");

                                                                    }
                                                                    case 4 -> { // Back
                                                                        innerBack = true;
                                                                        innerInnerBack = true;

                                                                    }
                                                                }
                                                            } catch (Exception e) {
                                                                System.out.println(e.getMessage());
                                                                scanner.nextLine();

                                                            }
                                                        }
                                                    }
                                                }



                                            }

                                        }

                                        case 2 -> {
                                            // View My Camps
                                            // TODO : can see the student roles meaning i just need to edit the summarize view
//                                            campStudentController.viewMyCamps(loginController.getUserID(), loginController.getFacultyName());
                                            int choice;
                                            boolean innerBack = false;
                                            ArrayList<String> filterCampNames = new ArrayList<>();

                                            if (!campStudentController.hasAnyCamps(loginController.getUserID(), loginController.getFacultyName())) {
                                                System.out.println("No camps to view");
                                            }
                                            else{
                                                while(true){
                                                    try {
                                                        innerBack = false;
                                                        System.out.println("*** View My Camps ***");

                                                        choice = new FilterViewController().menuSelectChoice();

                                                        if (choice == 6) // Back
                                                            break;


                                                        filterCampNames = campStudentController.viewCampsByFilter(choice,loginController.getUserID(), loginController.getFacultyName(), false);

                                                        if(filterCampNames == null){
                                                            continue;
                                                        }

                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input. Please enter a valid integer.");
                                                        scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
                                                    }


                                                    while(!innerBack){
                                                        try{
                                                            System.out.println("Select the camp index camp to view details (-1 to go back): ");
                                                            choice = scanner.nextInt();
                                                            if (choice == -1) {
                                                                innerBack = true;
                                                                break;
                                                            }

                                                            if (choice > filterCampNames.size() || choice < 1) {
                                                                throw new Exception("Invalid choice");
                                                            }

                                                        } catch (Exception e){
                                                            System.out.println("Invalid input");
                                                            scanner.nextLine();
                                                            continue;
                                                        }

                                                        String campID = filterCampNames.get(choice-1); //campStudentController.getCampNameToView(choice, loginController.getUserID(), loginController.getFacultyName());
                                                        campStudentController.getCampDetails(campID);

                                                        boolean innerInnerBack = false;
                                                        while(!innerInnerBack){ // they can  enquire and suggest, reports, withdraw, report back
                                                            try {
                                                                appViewsController.appRender(new CampStudentViewMyCampMenu());
                                                                pageSelection = scanner.nextInt();
                                                                switch (pageSelection) {

                                                                    case 1 -> {
                                                                        if(!campStudentController.isStudentAlreadyPartOfCampCommittee(campID, loginController.getUserID()))
                                                                            enquiryController.createQuery(loginController.getUserID(), campID);
                                                                        else
                                                                            System.out.println("You are a camp committee");
                                                                    }//Enquire
                                                                    case 2 -> {
                                                                        // Suggest
                                                                        // Need to check if the user is a camp committee
                                                                        if(campStudentController.isStudentAlreadyPartOfCampCommittee(campID, loginController.getUserID())) {
                                                                            suggestionController.createQuery(loginController.getUserID(), campID);
                                                                            // Add points to the student
                                                                            studentController.addPoints(loginController.getUserID(), 1);
                                                                        }else
                                                                            System.out.println("You are not a camp committee");

                                                                    }//Suggest

                                                                    case 3 -> {
                                                                        // TODO; GENERATE REPORT UNDER STUDENT
                                                                        // Have to check if they are a camp committee of the camp
//                                                                    A camp committee member can generate a report of the list of students attending
//                                                                    each camp that they have created. The list will include details of the camp as well as
//                                                                    the roles of the participants. There should be filters for how the camp committee
//                                                                    member would want to generate the list. (attendee, camp committee, etc.) (generate in
//                                                                    either txt or csv format).


                                                                        System.out.println("************* Generate Report of Attendees *************");

                                                                        // Report controller
                                                                        ReportController reportController = new ReportController();

                                                                        // For camp committee return all the camps under them
                                                                        String campName = campStudentController.getCampNameOfCampCommittee(loginController.getUserID());

                                                                        if (campName == null){
                                                                            System.out.println("You are not a camp committee of any camp");
                                                                        }else{

                                                                            // Get the name of the staff in charge
                                                                            String staffName = staffController.getUserActualName(campStaffController.getStaffInChargeIDOfCamp(campID));

                                                                            // Compile the data and pass it into the report controller
                                                                            ArrayList<String>  participants = campStudentController.getParticipantsInCamp(campName);
                                                                            ArrayList<String>  campCommittees  = campStudentController.getCampCommitteeInCamp(campName);
                                                                            ArrayList<String>  blacklists = campStudentController.getBlacklistInCamp(campName);


                                                                            // Get the actual names of the participants
                                                                            ArrayList<String> participantsNames = new ArrayList<String>();
                                                                            ArrayList<String> campCommitteesNames = new ArrayList<String>();
                                                                            ArrayList<String> blacklistsNames = new ArrayList<String>();

                                                                            for(String participant : participants){
                                                                                participantsNames.add(studentController.getUserActualName(participant));
                                                                            }

                                                                            for(String campCommittee : campCommittees){
                                                                                campCommitteesNames.add(studentController.getUserActualName(campCommittee));
                                                                            }

                                                                            for(String blacklist : blacklists){
                                                                                blacklistsNames.add(studentController.getUserActualName(blacklist));
                                                                            }

                                                                            ArrayList<Object> details = campStaffController.getCampDetailsForReport(campName);


                                                                            reportController.addCampDetailsToReport(campName, (Date) details.get(1), (Date) details.get(2), (Date) details.get(3), (String) details.get(4), (String) details.get(5), (int) details.get(6), (int) details.get(7), (String) details.get(8), staffName, (boolean) details.get(9) ,participantsNames, campCommitteesNames, blacklistsNames);




                                                                            while(true){
                                                                                try{
                                                                                    System.out.println("Select an option to generate report: ");
                                                                                    System.out.println("1. Generate in txt format");
                                                                                    System.out.println("2. Generate in csv format");
                                                                                    System.out.println("3. Back");
                                                                                    int reportChoice = scanner.nextInt();

                                                                                    switch (reportChoice){
                                                                                        case 1 -> { // Generate in txt format

                                                                                            IReport report = new GenerateTxt();
                                                                                            reportController.generateReportOfAttendees(report);

                                                                                        }
                                                                                        case 2 -> { // Generate in csv format
                                                                                            IReport report = new GenerateCsv();
                                                                                            reportController.generateReportOfAttendees(report);
                                                                                        }
                                                                                        case 3 -> {
                                                                                            //Back

                                                                                        }
                                                                                        default -> {
                                                                                            throw new Exception("Invalid input");
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }catch (Exception e) {
                                                                                    System.out.println("Invalid input");
                                                                                    scanner.nextLine();
                                                                                }
                                                                            }

                                                                        }


                                                                    }// Reports

                                                                    case 4 -> {
                                                                        boolean withdraw = campStudentController.withdrawFromCamp(campID, loginController.getUserID());
                                                                        if(withdraw){
                                                                            studentController.removeCampFromList(loginController.getUserID(), campID);
                                                                            // Add user to black list
                                                                            campStudentController.addStudentToBlacklist(campID, loginController.getUserID());
                                                                        }

                                                                    } // Withdraw



                                                                    case 5 -> { // Back
                                                                        innerInnerBack = true;
                                                                        innerBack = true;
                                                                        // refresh the page
//                                                                    campStaffController.viewMyCamps(loginController.getUserID(),  loginController.getFacultyName());
                                                                    }
                                                                    default -> throw new Exception("Invalid input");
                                                                }


                                                            }catch (Exception e){
                                                                System.out.println("Invalid input");
                                                                scanner.nextLine();
                                                            }
                                                        }
                                                    }
                                                }



                                            }


                                        }
                                        case 3 -> {
                                            // View my enquiries (Show camp name, enquiry, status, reply)
                                            // Need to check if the user is PART of any a camp committee
                                            // if 1. then we can view their own enquries
                                            // If status is pending, we can edit / delete

                                            // if 2. then we can view enquries of other only for the camp i am in charge
                                            // If status is pending, we can reply, then change to APPROVED, then add points to the student
                                            boolean innerBack = false;
                                            while (!innerBack){
                                                try{
                                                    appViewsController.appRender(new CampStudentMainEnquiriesMenu());
                                                    int choice = scanner.nextInt();

                                                    switch (choice){
                                                        case 1 -> {
                                                            // View my enquiries
                                                            enquiryController.viewMyQuery(loginController.getUserID());

                                                            if(enquiryController.hasAnyQuery(loginController.getUserID())){
                                                                boolean innerInnerBack = false;
                                                                while(!innerInnerBack){
                                                                    try {
                                                                        System.out.println("Select the enquiry index to view details (-1 to go back): ");
                                                                        int enquiryChoice = scanner.nextInt();
                                                                        if (enquiryChoice == -1) {
                                                                            innerInnerBack = true;
                                                                            break;
                                                                        }

                                                                        if (enquiryChoice > enquiryController.getAllQueryByUserID(loginController.getUserID()).size() || enquiryChoice < 1) {
                                                                            throw new Exception("Invalid choice");
                                                                        }

                                                                        String enquiryID = enquiryController.getMyQueryFromIdx(enquiryChoice, loginController.getUserID());
                                                                        enquiryController.showQueryDetails(enquiryID);

                                                                        boolean innerInnerInnerBack = false;
                                                                        while(!innerInnerInnerBack){
                                                                            try {
                                                                                appViewsController.appRender(new CampStudentViewMyQueriesMenu());
                                                                                int enquiryInnerChoice = scanner.nextInt();

                                                                                switch (enquiryInnerChoice){
                                                                                    case 1 -> {
                                                                                        // Edit enquiry
                                                                                        enquiryController.editQuery(loginController.getUserID(), enquiryID);
                                                                                        // Refresh the page
                                                                                        enquiryController.showQueryDetails(enquiryID);
                                                                                    }
                                                                                    case 2 -> {
                                                                                        // Delete enquiry
                                                                                        enquiryController.deleteQuery(loginController.getUserID(), enquiryID);

                                                                                        if(!enquiryController.hasAnyQuery(loginController.getUserID())){
                                                                                            innerInnerInnerBack = true;
                                                                                            innerInnerBack = true;
                                                                                        } else {
                                                                                            enquiryController.viewMyQuery(loginController.getUserID());
                                                                                        }
                                                                                    }
                                                                                    case 3 -> {
                                                                                        // Back
                                                                                        innerInnerInnerBack = true;
                                                                                        enquiryController.viewMyQuery(loginController.getUserID());
                                                                                    }
                                                                                    default -> {
                                                                                        throw new Exception("Invalid input");
                                                                                    }
                                                                                }
                                                                            }catch (Exception e){
                                                                                System.out.println("Invalid input");
                                                                                scanner.nextLine();
                                                                            }
                                                                        }

                                                                    }catch (Exception e){
                                                                        System.out.println("Invalid input");
                                                                        scanner.nextLine();
                                                                    }
                                                                }
                                                            }



                                                        }
                                                        case 2 -> {
                                                            // View enquiries of other students
                                                            if (!campStudentController.isStudentAlreadyPartOfCampCommitteeOfAnotherCamp(loginController.getUserID())){
                                                                System.out.println("You are not a camp committee of any camp");

                                                            }else{
                                                                // Get the campID of the camp committee the student is in

                                                                // Check if studet is a camp committee of any camp
                                                                // If yes, then we can view all enquiries of that camp

                                                                String campID = campStudentController.getCampNameOfCampCommittee(loginController.getUserID());

                                                                enquiryController.viewAllQueryOfThatCamp(campID);

                                                                if(enquiryController.hasAnyQueryOfThatCamp(campID)){
                                                                    boolean innerInnerBack = false;
                                                                    while(!innerInnerBack){
                                                                        try {
                                                                            System.out.println("Select the enquiry index to view details (-1 to go back): ");
                                                                            int enquiryChoice = scanner.nextInt();
                                                                            if (enquiryChoice == -1) {
                                                                                innerInnerBack = true;
                                                                                break;
                                                                            }

                                                                            if (enquiryChoice > enquiryController.getAllQueryByCampID(campID).size() || enquiryChoice < 1) {
                                                                                throw new Exception("Invalid choice");
                                                                            }

                                                                            String enquiryID = enquiryController.getCampQueryFromIdx(enquiryChoice, campID);
                                                                            enquiryController.showQueryDetails(enquiryID);

                                                                            boolean innerInnerInnerBack = false;
                                                                            while(!innerInnerInnerBack){
                                                                                try {
                                                                                    appViewsController.appRender(new CampStudentViewOtherQueriesMenu());
                                                                                    int enquiryInnerChoice = scanner.nextInt();

                                                                                    switch (enquiryInnerChoice){
                                                                                        case 1 -> {
                                                                                            // Reply enquiry
                                                                                            boolean valid = enquiryController.replyToQuery(enquiryID, loginController.getUserID());

                                                                                            if(valid)
                                                                                                studentController.addPoints(loginController.getUserID(), 1);
                                                                                            // Refresh the page
                                                                                            enquiryController.showQueryDetails(enquiryID);
                                                                                        }
                                                                                        case 2 -> {
                                                                                            // Back
                                                                                            innerInnerInnerBack = true;
                                                                                            enquiryController.viewAllQueryOfThatCamp(campID);
                                                                                        }
                                                                                        default -> {
                                                                                            throw new Exception("Invalid input");
                                                                                        }
                                                                                    }
                                                                                }catch (Exception e){
                                                                                    System.out.println("Invalid input");
                                                                                    scanner.nextLine();
                                                                                }
                                                                            }

                                                                        }catch (Exception e){
                                                                            System.out.println("Invalid input");
                                                                            scanner.nextLine();
                                                                        }
                                                                    }
                                                                }

                                                            }

                                                        }
                                                        case 3 -> {
                                                            // Back
                                                            innerBack = true;
                                                        }
                                                        default -> {
                                                            throw new Exception("Invalid input");
                                                        }
                                                    }

                                                } catch (Exception e){
                                                    System.out.println("Invalid input");
                                                    scanner.nextLine();
                                                }
                                            }

                                        }

                                        case 4 -> {
                                            // View my suggestions (show camp name, suggestion, status)
                                            // Need to check if user is PART of any camp committee
                                            // if 1. then we can view our own suggestions
                                            // If status is pending, we can edit / delete

                                            boolean innerBack = false;
                                            while (!innerBack) {
                                                try {
                                                    appViewsController.appRender(new CampStudentMainSuggestionsMenu());
                                                    int choice = scanner.nextInt();

                                                    switch (choice) {
                                                        case 1 -> {
                                                            // View my suggestions
                                                            suggestionController.viewMyQuery(loginController.getUserID());

                                                            if (suggestionController.hasAnyQuery(loginController.getUserID())) {
                                                                boolean innerInnerBack = false;
                                                                while (!innerInnerBack) {
                                                                    try {
                                                                        System.out.println("Select the suggestion index to view details (-1 to go back): ");
                                                                        int enquiryChoice = scanner.nextInt();
                                                                        if (enquiryChoice == -1) {
                                                                            innerInnerBack = true;
                                                                            break;
                                                                        }

                                                                        if (enquiryChoice > suggestionController.getAllQueryByUserID(loginController.getUserID()).size() || enquiryChoice < 1) {
                                                                            throw new Exception("Invalid choice");
                                                                        }

                                                                        String enquiryID = suggestionController.getMyQueryFromIdx(enquiryChoice, loginController.getUserID());
                                                                        suggestionController.showQueryDetails(enquiryID);

                                                                        boolean innerInnerInnerBack = false;
                                                                        while (!innerInnerInnerBack) {
                                                                            try {
                                                                                appViewsController.appRender(new CampStudentViewMyQueriesMenu());
                                                                                int enquiryInnerChoice = scanner.nextInt();

                                                                                switch (enquiryInnerChoice) {
                                                                                    case 1 -> {
                                                                                        // Edit suggestion
                                                                                        suggestionController.editQuery(loginController.getUserID(), enquiryID);
                                                                                        // Refresh the page
                                                                                        suggestionController.showQueryDetails(enquiryID);
                                                                                    }
                                                                                    case 2 -> {
                                                                                        // Delete suggestion
                                                                                        suggestionController.deleteQuery(loginController.getUserID(), enquiryID);

                                                                                        if(!suggestionController.isQueryExist(enquiryID)) { // TODO: Do we deduct score if they manage to delete suggestion ?
                                                                                            studentController.addPoints(loginController.getUserID(), -1);
                                                                                        }

                                                                                        if (!suggestionController.hasAnyQuery(loginController.getUserID())) {
                                                                                            innerInnerInnerBack = true;
                                                                                            innerInnerBack = true;
                                                                                        } else {
                                                                                            suggestionController.viewMyQuery(loginController.getUserID());
                                                                                        }
                                                                                    }
                                                                                    case 3 -> {
                                                                                        // Back
                                                                                        innerInnerInnerBack = true;
                                                                                        suggestionController.viewMyQuery(loginController.getUserID());
                                                                                    }
                                                                                    default -> {
                                                                                        throw new Exception("Invalid input");
                                                                                    }
                                                                                }
                                                                            } catch (Exception e) {
                                                                                System.out.println("Invalid input");
                                                                                scanner.nextLine();
                                                                            }
                                                                        }

                                                                    } catch (Exception e) {
                                                                        System.out.println("Invalid input");
                                                                        scanner.nextLine();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        case 2 -> {
                                                            // Back
                                                            innerBack = true;
                                                        }
                                                    }

                                                } catch (Exception e) {
                                                    System.out.println("Invalid input");
                                                    scanner.nextLine();
                                                }
                                            }
                                        }

                                        case 5 -> {
                                            // Back
                                            back = true;
                                        }
                                        default -> throw new Exception("Invalid input");
                                    }

                                }catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    scanner.nextLine();
                                }

                            }

                        }

                        case 4 -> { // Logout
                            loginController.logout();
                            isLogin = false;
                        }
                        default -> System.out.println("Invalid input");
                    }
                } catch (Exception e){
                    System.out.println("Invalid input");
                    scanner.nextLine();
                }

            }

            // Staff domain
            while(isLogin && domain == 2){
                int pageSelection;
                try{
                    // Do all the other stuff here
                    appViewsController.showHomePage();
                    pageSelection = scanner.nextInt();

                    // Allow the user to select the page and go back to the home page
                    switch (pageSelection) {
                        case 1 -> { // Accounts
                            while (true){
                                try {
                                    appViewsController.showAccountViewPage();
                                    pageSelection = scanner.nextInt();
                                    switch (pageSelection) {
                                        case 1 -> { // Change password

                                            loginController.setPassword(staffController);
                                            System.out.println("Password changed successfully");
                                        }
                                        case 2 -> {
                                        } // Back

                                        default -> throw new Exception("Invalid input");
                                    }

                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid input");
                                    scanner.nextLine();
                                }
                            }
                        }

                        case 2 -> { // Profile
                            System.out.println("Feature coming next update !");
                        }


                        case 3 -> { // Camps
                            back = false;
                            while(!back){
                                try {
                                    appViewsController.appRender(new CampStaffMainCampMenu());
                                    pageSelection = scanner.nextInt();

                                    switch (pageSelection) {
                                        case 1 -> { // Create camp //TODO: undo this
                                            // Retrieve faculty name from the user
                                            String facultyName = loginController.getFacultyName();
                                            String campName = campStaffController.createCamp(loginController.getUserID(), facultyName);

                                            // Check if campName is not empty string
                                            if(!campName.isEmpty()){
                                                // Add the camp to the staff
                                                staffController.addCampToList(loginController.getUserID(), campName);
                                            }




                                        }
                                        case 2 -> { // View All Camps
                                            //TODO : FOR campStudentController & campStaffController, need to let them filter by . NEED to implement default view in alhphabetical order
                                            int choice;
                                            boolean innerBack = false;
                                            ArrayList<String> filterCampNames = new ArrayList<String>();

                                            if(campStaffController.isCampsCreated()){
                                                System.out.println("No camps to view");
                                            }
                                            else{
                                                while (true) {
                                                    try {
                                                        innerBack = false;
                                                        System.out.println("*** View All Camps ***");

                                                        choice =  new FilterViewController().menuSelectChoice();

                                                        if (choice == 6) // Back
                                                            break;


                                                        filterCampNames = campStaffController.viewCampsByFilter(choice, loginController.getUserID(), loginController.getFacultyName(), true);

                                                        if (filterCampNames == null) {
                                                            continue;
                                                        }

                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input. Please enter a valid integer.");
                                                        scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
                                                    }

                                                    while(!innerBack) {
                                                        try{
                                                            System.out.println("Select the camp index to view details (-1 to go back): ");
                                                            choice = scanner.nextInt();
                                                            if (choice == -1) {
                                                                innerBack = true;
                                                                break;
                                                            }

                                                            if (choice > filterCampNames.size() || choice < 1) {
                                                                throw new Exception("Invalid choice");
                                                            }

                                                        } catch (Exception e) {
                                                            System.out.println(e.getMessage());
                                                            scanner.nextLine();
                                                            continue;
                                                        }

                                                        String campID = filterCampNames.get(choice-1);
                                                        campStaffController.getCampDetails(campID);
                                                        break;
                                                    }

                                                }



                                            }


                                        }
                                        case 3 -> {
                                            // View My Camps
                                            int choice;
                                            boolean innerBack = false;
                                            ArrayList<String> filterCampNames = new ArrayList<String>();

                                            if (!campStaffController.hasAnyCamps(loginController.getUserID())) {
                                                System.out.println("No camps to view");
                                            } else{
                                                while (true) {
                                                    try {
                                                        innerBack = false;
                                                        System.out.println("*** View My Camps ***");

                                                        choice =  new FilterViewController().menuSelectChoice();

                                                        if (choice == 6) // Back
                                                            break;

                                                        filterCampNames = campStaffController.viewCampsByFilter(choice, loginController.getUserID(), loginController.getFacultyName(), false);

                                                        if (filterCampNames == null) {
                                                            continue;
                                                        }

                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input. Please enter a valid integer.");
                                                        scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
                                                    }

                                                    while(!innerBack){

                                                        try {
                                                            System.out.println("Select the camp index to view details (-1 to go back): ");
                                                            choice = scanner.nextInt();
                                                            if (choice == -1) {
                                                                innerBack = true;
                                                                break;
                                                            }

                                                            if (choice > filterCampNames.size() || choice < 1) {
                                                                throw new Exception("Invalid choice");
                                                            }


                                                        } catch (Exception e) {
                                                            System.out.println(e.getMessage());
                                                            scanner.nextLine();
                                                            campStaffController.viewMyCamps(loginController.getUserID(), loginController.getFacultyName());
                                                            continue;
                                                        }


                                                        String campID = filterCampNames.get(choice-1);
                                                        campStaffController.getCampDetails(campID);



                                                        boolean innerInnerBack = false;
                                                        while(!innerInnerBack){
                                                            try {
                                                                appViewsController.appRender(new CampStaffViewMyCampMenu());
                                                                pageSelection = scanner.nextInt();
                                                                switch (pageSelection) {
                                                                    case 1 -> { // Edit Camp

                                                                        // Access through query controller and pass in the campID
                                                                        campStaffController.editCamp(loginController.getUserID(), campID, loginController.getFacultyName());
                                                                        // Refresh the page
                                                                        campStaffController.getCampDetails(campID);
                                                                    }
                                                                    case 2 -> { // View Suggestions


                                                                        // They can view all suggestions with PENDING status only if the campID is the same as the camp they are in charge of
                                                                        // They can go into each suggestion and accept or reject and update the querystatus
                                                                        // If accept, add points to the student
                                                                        suggestionController.viewAllQueryOfThatCamp(campID);
                                                                        boolean innerInnerInnerBack = false;
                                                                        if (suggestionController.hasAnyQueryOfThatCamp(campID)){
                                                                            while (!innerInnerInnerBack){

                                                                                try{
                                                                                    System.out.println("Select the suggestion index to view details (-1 to go back): ");

                                                                                    choice = scanner.nextInt();
                                                                                    if (choice == -1) {
                                                                                        innerBack = true;
                                                                                        break;
                                                                                    }

                                                                                    if (choice > suggestionController.getAllQueryByCampID(campID).size() || choice < 1) {
                                                                                        throw new Exception("Invalid choice");
                                                                                    }

                                                                                    String suggestionID = suggestionController.getCampQueryFromIdx(choice, campID);
                                                                                    suggestionController.showQueryDetails(suggestionID);

                                                                                    boolean innerInnerInnerInnerBack = false;
                                                                                    while(!innerInnerInnerInnerBack){
                                                                                        try {
                                                                                            appViewsController.appRender(new CampStaffViewMyCampSuggestionsMenu());
                                                                                            pageSelection = scanner.nextInt();
                                                                                            switch (pageSelection) {
                                                                                                case 1 -> { // Accept suggestion
                                                                                                    boolean valid = suggestionController.approvalToQuery(suggestionID, loginController.getUserID(), QueryStatus.APPROVED);

                                                                                                    // Get the userID and add 1 point
                                                                                                    if(valid)
                                                                                                        studentController.addPoints(suggestionController.getAuthor(suggestionID), 1);
                                                                                                    // Refresh the page
                                                                                                    suggestionController.showQueryDetails(suggestionID);
                                                                                                }
                                                                                                case 2 -> { // Reject suggestion
                                                                                                    suggestionController.approvalToQuery(suggestionID, loginController.getUserID(), QueryStatus.DENIED);
                                                                                                    // Refresh the page
                                                                                                    suggestionController.showQueryDetails(suggestionID);
                                                                                                }
                                                                                                case 3 -> { // Back
                                                                                                    innerInnerInnerInnerBack = true;
                                                                                                    suggestionController.viewAllQueryOfThatCamp(campID);
                                                                                                }
                                                                                                default -> {
                                                                                                    throw new Exception("Invalid input");
                                                                                                }
                                                                                            }
                                                                                        }catch (Exception e){
                                                                                            System.out.println("Invalid input");
                                                                                            scanner.nextLine();
                                                                                        }
                                                                                    }

                                                                                } catch (Exception e){
                                                                                    System.out.println("Invalid input");
                                                                                    scanner.nextLine();
                                                                                }
                                                                            }
                                                                        }


                                                                    }
                                                                    case 3 -> {
                                                                        // View Enquiries //Note that they cant create enquiry only student can
                                                                        //TODO :
                                                                        // Access through query controller and pass in the campID
                                                                        // Add the points to the student if accept the suggestion through student controller

                                                                        // THey can view all enquries  with PENDING status
                                                                        // They can go into each enquiry and reply and update the querystatus

                                                                        enquiryController.viewAllQueryOfThatCamp(campID);
                                                                        boolean innerInnerInnerBack = false;
                                                                        if(enquiryController.hasAnyQueryOfThatCamp(campID)){
                                                                            while (!innerInnerInnerBack){
                                                                                try{
                                                                                    System.out.println("Select the enquiry index to view details (-1 to go back): ");

                                                                                    choice = scanner.nextInt();
                                                                                    if (choice == -1) {
                                                                                        innerBack = true;
                                                                                        break;
                                                                                    }

                                                                                    if (choice > enquiryController.getAllQueryByCampID(campID).size() || choice < 1) {
                                                                                        throw new Exception("Invalid choice");
                                                                                    }

                                                                                    String suggestionID = enquiryController.getCampQueryFromIdx(choice, campID);
                                                                                    enquiryController.showQueryDetails(suggestionID);

                                                                                    boolean innerInnerInnerInnerBack = false;
                                                                                    while(!innerInnerInnerInnerBack){
                                                                                        try{
                                                                                            appViewsController.appRender(new CampStaffViewMyCampEnquiriesMenu());
                                                                                            pageSelection = scanner.nextInt();

                                                                                            switch (pageSelection) {
                                                                                                case 1 -> { // Reply enquiry
                                                                                                    enquiryController.replyToQuery(suggestionID, loginController.getUserID());
                                                                                                    // Refresh the page
                                                                                                    enquiryController.showQueryDetails(suggestionID);
                                                                                                }
                                                                                                case 2 -> { // Back
                                                                                                    innerInnerInnerInnerBack = true;
                                                                                                    enquiryController.viewAllQueryOfThatCamp(campID);
                                                                                                }
                                                                                                default -> {
                                                                                                    throw new Exception("Invalid input");
                                                                                                }
                                                                                            }

                                                                                        }catch (Exception e){
                                                                                            System.out.println("Invalid input");
                                                                                            scanner.nextLine();
                                                                                        }
                                                                                    }


                                                                                }catch (Exception e){
                                                                                    System.out.println("Invalid input");
                                                                                    scanner.nextLine();
                                                                                }
                                                                            }
                                                                        }

                                                                    }
                                                                    case 4 -> { // Delete Camp

                                                                        campStaffController.deleteCamp(loginController.getUserID(), campID);
                                                                        staffController.removeCampFromList(loginController.getUserID(), campID);

                                                                        if (!campStaffController.hasAnyCamps(loginController.getUserID())){ // If the user has no more camp
                                                                            innerInnerBack = true;
                                                                            innerBack = true;
                                                                        } else { // Refresh the page
//                                                                        campStaffController.viewMyCamps(loginController.getUserID(), loginController.getFacultyName());
                                                                        }
                                                                    }
                                                                    case 5 -> { // Back
                                                                        innerInnerBack = true;
                                                                        innerBack = true;
                                                                        // refresh the page
//                                                                    campStaffController.viewMyCamps(loginController.getUserID(),  loginController.getFacultyName());
                                                                    }
                                                                    default -> throw new Exception("Invalid input");

                                                                }

                                                            }catch (Exception e){
                                                                System.out.println("Invalid input");
                                                                scanner.nextLine();
                                                            }
                                                        }
                                                    }
                                                }





                                            }



                                        }

                                        case 4 -> {
                                            // Report
                                            boolean innerBack = false;
                                            while(!innerBack) {
                                                try {
                                                    appViewsController.appRender(new CampStaffMainCampReportsMenu());
                                                    pageSelection = scanner.nextInt();
                                                    switch (pageSelection) {
                                                        case 1 -> {
                                                            // Generate Report of Students
//                                                            A staff can generate a report of the list of students attending each camp that his/her
//                                                            has created. The list will include details of the camp as well as the roles of the
//                                                            participants. There should be filters for how the staff would want to generate the list.
//                                                            (attendee, camp committee, etc.) (generate in either txt or csv format).


                                                            // From staff controller, we get the list of campName, then ask the staff to select a camp
                                                            // We can ask if they wanna filter camps by partiicpant/committee/all
                                                            // campstaffcontroller.getParticipantList(campID) : ArrayList<String>
                                                            // or campstaffcontroller.getCommitteeList(campID) : ArrayList<String>

                                                            // After that can use studentController.getName(String userID) : String

                                                            //  We can combine the camp details + the roles of the participants + the name of the participants

                                                            // We can ask if they wanna generate in txt or csv format

                                                            System.out.println("************* Generate Report of Attendees *************");

                                                            // Report controller
                                                            ReportController reportController = new ReportController();

                                                            // For staff return all the camps under them
                                                            ArrayList<String> campNames = campStaffController.getCampNames(loginController.getUserID(), loginController.getFacultyName());
                                                            if (!campStaffController.hasAnyCamps(loginController.getUserID())){
                                                                System.out.println("No camps to view");
                                                            }else{

                                                                // Get the name of the staff in charge
                                                                String staffName = staffController.getUserActualName(loginController.getUserID());

                                                                // Compile the data and pass it into the report controller
                                                                for(String campName : campNames){
                                                                    ArrayList<String>  participants = campStaffController.getParticipantsInCamp(campName);
                                                                    ArrayList<String>  campCommittees  = campStaffController.getCampCommitteeInCamp(campName);
                                                                    ArrayList<String>  blacklists = campStaffController.getBlacklistInCamp(campName);


                                                                    // Get the actual names of the participants
                                                                    ArrayList<String> participantsNames = new ArrayList<String>();
                                                                    ArrayList<String> campCommitteesNames = new ArrayList<String>();
                                                                    ArrayList<String> blacklistsNames = new ArrayList<String>();

                                                                    for(String participant : participants){
                                                                        participantsNames.add(studentController.getUserActualName(participant));
                                                                    }

                                                                    for(String campCommittee : campCommittees){
                                                                        campCommitteesNames.add(studentController.getUserActualName(campCommittee));
                                                                    }

                                                                    for(String blacklist : blacklists){
                                                                        blacklistsNames.add(studentController.getUserActualName(blacklist));
                                                                    }

                                                                    ArrayList<Object> details = campStaffController.getCampDetailsForReport(campName);


                                                                   reportController.addCampDetailsToReport(campName, (Date) details.get(1), (Date) details.get(2), (Date) details.get(3), (String) details.get(4), (String) details.get(5), (int) details.get(6), (int) details.get(7), (String) details.get(8), staffName, (boolean) details.get(9) ,participantsNames, campCommitteesNames, blacklistsNames);


                                                                }

                                                                while(true){
                                                                    try{
                                                                        System.out.println("Select an option to generate report: ");
                                                                        System.out.println("1. Generate in txt format");
                                                                        System.out.println("2. Generate in csv format");
                                                                        System.out.println("3. Back");
                                                                        int reportChoice = scanner.nextInt();

                                                                        switch (reportChoice){
                                                                            case 1 -> { // Generate in txt format

                                                                                IReport report = new GenerateTxt();
                                                                                reportController.generateReportOfAttendees(report);

                                                                            }
                                                                            case 2 -> { // Generate in csv format
                                                                                IReport report = new GenerateCsv();
                                                                                reportController.generateReportOfAttendees(report);
                                                                                //TODO : Generate performance report of in CSV not dne under generateCSV.java

                                                                            }
                                                                            case 3 -> {
                                                                                //Back

                                                                            }
                                                                            default -> {
                                                                                throw new Exception("Invalid input");
                                                                            }
                                                                        }
                                                                        break;
                                                                    }catch (Exception e) {
                                                                        System.out.println("Invalid input");
                                                                        scanner.nextLine();
                                                                    }
                                                                }

                                                            }



                                                        }
                                                        case 2 -> { // Generate Performance Report
                                                            System.out.println("************* Generate Performance Report of Camp Committee *************");
                                                            //TODO : Generate performance repot of camp committee

                                                            ReportController reportController = new ReportController();

                                                            HashMap<String, Integer> pointsMap = new HashMap<>();


                                                            // For staff return all the camps under them
                                                            ArrayList<String> campNames = campStaffController.getCampNames(loginController.getUserID(), loginController.getFacultyName());

                                                            if(!campStaffController.hasAnyCamps(loginController.getUserID())){
                                                                System.out.println("No camps to view");
                                                            }else {

                                                                // Get the name of the staff in charge
                                                                String staffName = staffController.getUserActualName(loginController.getUserID());



                                                                // Compile the data and pass it into the report controller
                                                                for(String campName : campNames){
                                                                    ArrayList<String>  participants = campStaffController.getParticipantsInCamp(campName);
                                                                    ArrayList<String>  campCommittees  =campStaffController.getCampCommitteeInCamp(campName);
                                                                    ArrayList<String>  blacklists =campStaffController.getBlacklistInCamp(campName);


                                                                    // Get the actual names of the participants
                                                                    ArrayList<String> participantsNames = new ArrayList<String>();
                                                                    ArrayList<String> campCommitteesNames = new ArrayList<String>();
                                                                    ArrayList<String> blacklistsNames = new ArrayList<String>();

                                                                    for(String participant : participants){
                                                                        participantsNames.add(studentController.getUserActualName(participant));
                                                                    }

                                                                    for(String campCommittee : campCommittees){
                                                                        campCommitteesNames.add(studentController.getUserActualName(campCommittee));
                                                                    }
                                                                    Collections.sort(campCommitteesNames); //sort in alphabetical order
                                                                    //adds points to the array
                                                                    for (int i = 0; i < campCommittees.size(); i++) {
                                                                        //System.out.println(i);
//                                                                        studentController.addPoints(campCommittees.get(i), i+1); // TODO: remove this when done
//                                                                        System.out.println("Student name: "+ studentController.getUserActualName(campCommittees.get(i)));
//                                                                        System.out.println("Points: "+ studentController.getPoints(campCommittees.get(i)));

                                                                        pointsMap.put(studentController.getUserActualName(campCommittees.get(i)), studentController.getPoints(campCommittees.get(i)));

                                                                    }

                                                                    //store 1 dimensional arraylist into a 2 dimensional arraylist pointsList
                                                                    //pointsList.add(points);

                                                                    for(String blacklist : blacklists){
                                                                        blacklistsNames.add(studentController.getUserActualName(blacklist));
                                                                    }

                                                                    ArrayList<Object> details = campStaffController.getCampDetailsForReport(campName);


                                                                    reportController.addCampDetailsToReport(campName, (Date) details.get(1), (Date) details.get(2), (Date) details.get(3), (String) details.get(4), (String) details.get(5), (int) details.get(6), (int) details.get(7), (String) details.get(8), staffName, (boolean) details.get(9) ,participantsNames, campCommitteesNames, blacklistsNames);
                                                                }

                                                                try{
                                                                    System.out.println("Select an option to generate report: ");
                                                                    System.out.println("1. Generate in txt format");
                                                                    System.out.println("2. Generate in csv format");
                                                                    System.out.println("3. Back");
                                                                    int reportChoice = scanner.nextInt();


                                                                    switch (reportChoice){
                                                                        case 1 -> { // Generate in txt format

                                                                            IReport report = new GenerateTxt();

                                                                            reportController.generateReportOfCampCommittee(report, pointsMap);

                                                                        }
                                                                        case 2 -> { // Generate in csv format
                                                                            IReport report = new GenerateCsv();
                                                                            reportController.generateReportOfCampCommittee(report, pointsMap);
                                                                        }
                                                                        case 3 -> {
                                                                            //Back
                                                                            break;
                                                                        }
                                                                        default -> {
                                                                            throw new Exception("Invalid input");
                                                                        }
                                                                    }
                                                                    break;
                                                                } catch (Exception e) {
                                                                    System.out.println("Invalid input");
                                                                    scanner.nextLine();
                                                                }
                                                            }



                                                        }
                                                        case 3 -> { // Back
                                                            innerBack = true;
                                                        }

                                                    }
                                                }catch (Exception e){
                                                    System.out.println(e.getMessage());
                                                    scanner.nextLine();
                                                }
                                            }

                                        }

                                        case 5 -> {
                                            // Back
                                            back = true;
                                        }

                                        default -> throw new Exception("Invalid input");
                                    }

                                } catch (Exception e) {
                                    System.out.println("Invalid input");
                                    scanner.nextLine();
                                }
                            }

                        }

                        case 4 -> { // Logout
                            loginController.logout();
                            isLogin = false;
                        }
                        default -> System.out.println("Invalid input");
                    }

                }catch (Exception e) {
                    System.out.println("Invalid input");
                    scanner.nextLine();
                }

            }
        }

        //System.out.println("Test end here");

    }
}