package org.example.camps.controllers;

import org.example.camps.models.CampModel;
import org.example.camps.interfaces.ICampController;
import org.example.camps.interfaces.ICampView;
import org.example.camps.views.PrintCampDetails;
import org.example.filters.controllers.FilterViewController;

import java.util.*;


// Acts as the middle man between the view and the camp model
public class CampController{

    private HashMap<String, CampModel> camps;

    private ICampController staffController;
    private ICampController studentController;

    private ICampView view;

    public CampController() {
        camps = new HashMap<String, CampModel>();

    }

    public void createDummyCamps(int number){
        for(int i = 0; i < number; i++){
            String campName = "Camp " + (char) (i+65);
            Date[] dates = new Date[]{new Date(new Date().getTime() + (5 * 24 * 60 * 60 * 1000)),new Date(new Date().getTime() + (9 * 24 * 60 * 60 * 1000)) };
            // Set registration end date to 3 day after today
            Date registrationEndDate = new Date(new Date().getTime() + (3 * 24 * 60 * 60 * 1000));
            String location = "Location " + i;
            String accessibility = "NTU";
            int totalSlots = 10;
            int campCommitteeSlots = 5;
            String description = "Description " + i;
            String userID = "Staff " + i;
            boolean visibility = true;
            CampModel camp = new CampModel(campName, dates, registrationEndDate, location, accessibility, totalSlots, campCommitteeSlots, description, userID, visibility);

            camp.addStudBlacklist("YCHERN"); //CHERN
            camp.addStudBlacklist("KOH1");   //KOH
            camp.addStudBlacklist("BR015");  //BRANDON

            if(i%number == 0)
                camp.addCampCommittee("CT113");  //CALVIN

            if(i%number == 1)
                camp.addCampCommittee("YCN019"); //CHAN

            if(i%number == 2)
                camp.addCampCommittee("DL007");  //DENISE

            camp.addParticipant("DON84");   //DONG
            camp.addParticipant("ELI34");   //ERNEST
            camp.addParticipant("LE51");    //LEE


            if(i != 0){
                camp.addStudBlacklist("YCHERN"+i); //CHERN
                camp.addStudBlacklist("KOH1"+i);   //KOH
                camp.addStudBlacklist("BR015"+i);  //BRANDON

                if(i%number == 1)
                    camp.addCampCommittee("YCN019"+i); //CHAN

                if(i%number == 2)
                    camp.addCampCommittee("DL007"+i);  //DENISE

                camp.addParticipant("DON84"+i);   //DONG
                camp.addParticipant("ELI34"+i);   //ERNEST
                camp.addParticipant("LE51"+i);    //LEE
            }


            camp.setStaffInChargeID(i%2 == 0 ? "UPAM" : "ANWIT");

            camps.put(campName, camp);
        }
    }




    // #############################
    // #  Camp Controller Accessor #
    // #############################

    public void deleteCamp(String userID, String campName) {
        if (camps.containsKey(campName)) {
            if (camps.get(campName).getStaffInChargeID().equals(userID)) {

                //Check if there are any participants or camp committee in the camp
                if(!camps.get(campName).getParticipants().isEmpty() || !camps.get(campName).getCampCommittee().isEmpty()){
                    System.out.println("Camp cannot be deleted as there are participants or camp committee in the camp");
                    return;
                }

                camps.remove(campName);
                System.out.println("Camp deleted successfully");
            }
        } else {
            System.out.println("Camp does not exist or you are not the staff in charge of this camp");
        }
    }


    public void showCampDetails(String CampName){
        view = new PrintCampDetails(camps.get(CampName).getCampName(), camps.get(CampName).getDates(), camps.get(CampName).getRegistrationEndDate(), camps.get(CampName).getLocation(), camps.get(CampName).getCampAccessbility(), camps.get(CampName).getTotalSlots(), camps.get(CampName).getCampCommitteeSlots(), camps.get(CampName).getDescription(), camps.get(CampName).getStaffInChargeID(), camps.get(CampName).isVisbility(), camps.get(CampName).getParticipants(), camps.get(CampName).getCampCommittee(), camps.get(CampName).getBlacklist());
        view.display();
    }

    public boolean hasAnyCamps(String userID, boolean isStudent, String facultyName){
        if(!isStudent){
            for(CampModel camp : camps.values()){
                if(camp.getStaffInChargeID().equals(userID)){
                    return true;
                }
            }

        } else {
            for(CampModel camp : camps.values()){
                if(camp.getParticipants().contains(userID) || camp.getCampCommittee().contains(userID)){
                    return true;
                }
            }
        }
        return false;
    }

    // For staff return all the camps they created
    // For student return all the camps they joined
    public ArrayList<String> getCampsNames(String userID, boolean isStudent, String facultyName){


        ArrayList<String> campNames = new ArrayList<String>();

        if(!isStudent){
            for(CampModel camp : camps.values()){
                if(camp.getStaffInChargeID().equals(userID)){
                    campNames.add(camp.getCampName());
                }
            }
        } else {
            for(CampModel camp : camps.values()){
                if(camp.getParticipants().contains(userID) || camp.getCampCommittee().contains(userID)){
                    campNames.add(camp.getCampName());
                }
            }
        }

        return campNames;
    }

    // Grab the camp index under view my camps
    // For staff is the camps they created
    // For students is the camp they joined
    public String getCampName(int idx, String userID, boolean isStudent){
        int count = 1;

        if(!isStudent){
            // For staff
            for(CampModel camp : camps.values()){
                if(camp.getStaffInChargeID().equals(userID)){
                    if(count == idx){
                        return camp.getCampName();
                    }
                    count++;
                }
            }
        } else {
            // For student
            for(CampModel camp : camps.values()){
                if(camp.getParticipants().contains(userID) || camp.getCampCommittee().contains(userID)){
                    if(count == idx){
                        return camp.getCampName();
                    }
                    count++;
                }
            }
        }

        return null;
    }


    // ####################
    // #    Views         #
    // ####################

    private void render(ICampView view){
        view.display();
    }


    public ArrayList<String> viewMyCamps(String userID, boolean isStudent, String facultyName) {

        FilterViewController filter = new FilterViewController(camps);
        return filter.viewMyCamps(userID, isStudent, facultyName);
    }

    public ArrayList<String> viewCampsFilterByStartDate(String userID, boolean requireVisibility, String facultyName,Date parsedDate, boolean viewAll) {

        FilterViewController filter = new FilterViewController(camps);
        return filter.viewCampsFilterByStartDate(userID, requireVisibility, facultyName, parsedDate, viewAll);

    }

    public ArrayList<String> viewCampsFilterByRegDate(String userID, boolean requireVisibility, String facultyName, Date parsedDate, boolean viewAll) {
        FilterViewController filter = new FilterViewController(camps);
        return filter.viewCampsFilterByRegDate(userID, requireVisibility, facultyName, parsedDate, viewAll);
    }


    public ArrayList<String> viewCampsFilterByLocation(String userID, boolean requireVisibility, String facultyName, String findLocation, boolean viewAll) {
        FilterViewController filter = new FilterViewController(camps);
        return filter.viewCampsFilterByLocation(userID, requireVisibility, facultyName, findLocation, viewAll);
    }

    public ArrayList<String> viewCampsFilterByCampName(String userID, boolean requireVisibility, String facultyName, String campName, boolean viewAll) {
        FilterViewController filter = new FilterViewController(camps);
        return filter.viewCampsFilterByCampName(userID, requireVisibility, facultyName, campName, viewAll);
    }

    public ArrayList<String> viewAllCamps(String userID, boolean requireVisibility, String facultyName) {

        FilterViewController filter = new FilterViewController(camps);
        return filter.viewAllCamps(userID, requireVisibility, facultyName);

    }


    // ################
    // #  Validators  #
    // ################

    public boolean hasAnyCampsToView(String userID, String facultyName){
        for (CampModel camp : camps.values()) {
            if (camp.isVisbility() && (camp.getCampAccessbility().equals(facultyName) || camp.getCampAccessbility().equals("NTU"))) {
                return true;
            }
        }
        return false;
    }

    // Full as in all slots are completely filled up including the committee slots
    public boolean isCampFull(String campName){
        return camps.get(campName).getTotalSlots() == (camps.get(campName).getCampCommitteeSlots() + camps.get(campName).getParticipants().size());
    }

    public boolean isAllCampsEmpty(){
        return camps.isEmpty();
    }

    public boolean isAlreadyPartOfCampCommitteeOfAnotherCamp(String userID){
        for(CampModel camp : camps.values()){
            if(camp.getCampCommittee().contains(userID)){
                return true;
            }
        }
        return false;
    }

    public boolean isCampDateOverlapping(String campName){
        Date[] dateArr = getCampDates(campName);
        Date[] dateArr2;

        for(CampModel camp : camps.values()){
            if(!camp.getCampName().equals(campName)){

                dateArr2 = camp.getDates();

                // If start date of camp 1 is after start date of camp 2 and before end date of camp 2
                if(dateArr[0].after(dateArr2[0]) && dateArr[0].before(dateArr2[1])){
                    return true;
                }

                // Opposite of above
                if(dateArr[1].after(dateArr2[0]) && dateArr[1].before(dateArr2[1])){
                    return true;
                }
            }
        }
        return false;

    }

    // ########################
    // #  Accessor & Mutator  #
    // ########################

    public String getStaffInChargeID(String campName){
        return camps.get(campName).getStaffInChargeID();
    }

    public String getCampNameOfCampCommittee(String userID){
        for(CampModel camp : camps.values()){
            if(camp.getCampCommittee().contains(userID)){
                return camp.getCampName();
            }
        }
        return null;
    }

    // Note: Functions with ToView at the end is for students to interact when viewing all camps
    public String getCampNameToView(int idx, String userID, String facultyName){
        int count = 1;
        for(CampModel camp : camps.values()){
            if(camp.isVisbility() && (camp.getCampAccessbility().equals(facultyName) || camp.getCampAccessbility().equals("NTU"))){
                if(count == idx){
                    return camp.getCampName();
                }
                count++;
            }
        }
        return null;
    }

    public int getNumOfCampsToView(String userID, String facultyName){
        int count = 0;
        for (CampModel camp : camps.values()) {
            if (camp.isVisbility() && (camp.getCampAccessbility().equals(facultyName) || camp.getCampAccessbility().equals("NTU"))) {
                count++;
            }
        }
        return count;
    }

    public void addParticipant(String campName, String userID){
        camps.get(campName).addParticipant(userID);
    }

    public void removeParticipant(String campName, String userID){
        camps.get(campName).removeParticipant(userID);
    }

    public void addCampCommittee(String campName, String userID){
        camps.get(campName).addCampCommittee(userID);
    }

    public void addCamp(String campName, Date[] dates, Date registrationEndDate, String location, String accessibility, int totalSlots, int campCommitteeSlots, String description, String userID, boolean visibility) {
        CampModel camp = new CampModel(campName, dates, registrationEndDate, location, accessibility, totalSlots, campCommitteeSlots, description, userID, visibility);
        camps.put(campName, camp);
    }

    public ArrayList<String> getAllCampIDsUnderStaff(String userID){
        ArrayList<String> campIDs = new ArrayList<String>();
        for(CampModel camp : camps.values()){
            if(camp.getStaffInChargeID().equals(userID)){
                campIDs.add(camp.getCampName());
            }
        }
        return campIDs;
    }


    public void addStudentToBlacklist(String campName, String userID){
        camps.get(campName).addStudBlacklist(userID);
    }

    public boolean getIsCampNameTaken(String campName){
        return camps.containsKey(campName);
    }

    public ArrayList<Object> getCampDetailsForReport(String campName) {
        ArrayList<Object> campDetails = new ArrayList<Object>();
        campDetails.add(camps.get(campName).getCampName());
        campDetails.add(camps.get(campName).getDates()[0]);
        campDetails.add(camps.get(campName).getDates()[1]);
        campDetails.add(camps.get(campName).getRegistrationEndDate());
        campDetails.add(camps.get(campName).getLocation());
        campDetails.add(camps.get(campName).getCampAccessbility());
        campDetails.add(camps.get(campName).getTotalSlots());
        campDetails.add(camps.get(campName).getCampCommitteeSlots());
        campDetails.add(camps.get(campName).getDescription());
        campDetails.add(camps.get(campName).isVisbility());
        return campDetails;
    }

    public ArrayList<String> getCampBlacklist(String campID){
        return camps.get(campID).getBlacklist();
    }

    public boolean getCampVisibility(String campID){
        return camps.get(campID).isVisbility();
    }

    public Date[] getCampDates(String campID){
        return camps.get(campID).getDates();
    }

    public Date getCampRegistrationEndDate(String campID){
        return camps.get(campID).getRegistrationEndDate();
    }

    public int getCampTotalSlots(String campID){
        return camps.get(campID).getTotalSlots();
    }

    public int getCampCommitteeSlots(String campID){
        return camps.get(campID).getCampCommitteeSlots();
    }

    public ArrayList<String> getCampParticipants(String campID){
        return camps.get(campID).getParticipants();
    }

    public ArrayList<String> getCampCommittee(String campID){
        return camps.get(campID).getCampCommittee();
    }

    public void setCampDescription(String campID, String description){
        camps.get(campID).setDescription(description);
    }

    public void setLocation(String campID, String location){
        camps.get(campID).setLocation(location);
    }

    public void setCampAccessibility(String campID, String accessbility){
        camps.get(campID).setCampAccessbility(accessbility);
    }

    public void setCampVisibility(String campID, boolean visibility){
        camps.get(campID).setVisbility(visibility);
    }

    public void setStartDate(String campID, Date startDate){
        camps.get(campID).setDates(new Date[]{startDate, camps.get(campID).getDates()[1]});
    }

    public void setEndDate(String campID, Date endDate){
        camps.get(campID).setDates(new Date[]{camps.get(campID).getDates()[0], endDate});
    }

    public void setRegistrationEndDate(String campID, Date registrationEndDate){
        camps.get(campID).setRegistrationEndDate(registrationEndDate);
    }

    public void setCampTotalSlots(String campID, int totalSlots){
        camps.get(campID).setTotalSlots(totalSlots);
    }

    public void setCampCommitteeSlots(String campID, int committeeSlots){
        camps.get(campID).setCampCommitteeSlots(committeeSlots);
    }


}





//    public void viewMyCamps(String userID, boolean isStudent, String facultyName) {
//        int idx = 1;
//        System.out.println("#################################");
//        System.out.println("##     My Camps                ##");
//        System.out.println("#################################");
//        System.out.println();
//
//        if(camps.isEmpty()){
//            System.out.println("No camps created yet");
//            return;
//        }
//
//
//        if(!isStudent){
//            // For staff to see all the camps they created
//            for(CampModel camp : camps.values()){
//                if(camp.getStaffInChargeID().equals(userID)){
//                    System.out.print(idx+".");
//                    idx++;
//                    Date[] dates = camp.getDates();
//                    int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
//                    int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();
//                    (new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1], camp.getRegistrationEndDate(),remainingSlots,remainingCommitteeSlots)).display();
//                }
//            }
//        } else {
//
//            // For student to see all the camps they joined
//            for(CampModel camp : camps.values()){
//                if(camp.getParticipants().contains(userID) || camp.getCampCommittee().contains(userID)){
//                    System.out.print(idx+".");
//                    idx++;
//                    Date[] dates = camp.getDates();
//
//                    String role = "";
//                    // Check if student is a participant or a committee
//                    if(camp.getParticipants().contains(userID)){
//                        role = "Participant";
//                    } else {
//                        role = "Committee";
//                    }
//
//                    int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
//                    int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();
//                    (new PrintCampDetailsSummarizeStudent(camp.getCampName(), camp.getLocation(), dates[0], dates[1], camp.getRegistrationEndDate(),remainingSlots,remainingCommitteeSlots,role)).display();
//                }
//            }
//        }
//    }


//    public void viewAllCamps(String userID, boolean requireVisibility, String facultyName) {
//        int idx = 1;
//        System.out.println("#################################");
//        System.out.println("##     All Camps               ##");
//        System.out.println("#################################");
//        System.out.println();
//
//        boolean isAnyCampVisible = false;
//
//        if (camps.isEmpty()) {
//            System.out.println("No camps created yet");
//            return;
//        }
//
//       if(!requireVisibility){
//            // For staff
//           for (CampModel camp : camps.values()) {
//
//
//
//               System.out.print(idx + ".");
//               idx++;
//               Date[] dates = camp.getDates();
//               int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
//               int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();
//
//               this.view  = new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1], camp.getRegistrationEndDate(), remainingSlots, remainingCommitteeSlots);
//               this.view.display();
//           }
//       } else { // For student
//           // First check if there is any camp that is visible
//           // If no then i will print out "No camps created yet"
//            // If yes then i will print out the camps that are visible and under the faculty/NTU
//            for (CampModel camp : camps.values()) {
//                if (camp.isVisbility() && (camp.getCampAccessbility().equals(facultyName) || camp.getCampAccessbility().equals("NTU"))) {
//                    isAnyCampVisible = true;
//                    System.out.print(idx + ".");
//                    idx++;
//                    Date[] dates = camp.getDates();
//                    int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
//                    int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();
//
//                    this.view = new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1], camp.getRegistrationEndDate(), remainingSlots, remainingCommitteeSlots);
//                    this.view.display();
//                }
//
//            }
//
//            if(!isAnyCampVisible){
//                System.out.println("No camps created yet");
//            }
//
//       }
//
//    }
//
