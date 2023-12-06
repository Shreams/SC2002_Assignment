package org.example.camps.controllers;

import org.example.camps.models.CampModel;
import org.example.camps.interfaces.ICampView;
import org.example.camps.views.PrintCampDetails;
import org.example.filters.controllers.FilterViewController;

import java.util.*;


/**
 * This is the CampController class responsible for managing camps.
 * Relies on the FilterViewController class to handle camp filtering operations.
 * {@link FilterViewController}
 *
 * @author Group1
 * @version 1.0
 */
public class CampController{

    private HashMap<String, CampModel> camps;

    private ICampView view;


    /**
     * Constructor for the CampController class.
     * Initializes the camps HashMap.
     */
    public CampController() {
        camps = new HashMap<String, CampModel>();

    }

    /**
     * Generates a specified number of dummy camps with random data.
     *
     * @param number The number of dummy camps to create.
     */
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

    /**
     * Deletes a camp if the user has the permission to do so and if there are no participants or camp committee members.
     *
     * @param userID   The user ID attempting to delete the camp.
     * @param campName The name of the camp to be deleted.
     */
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


    /**
     * Displays details of a specific camp.
     *
     * @param campName The name of the camp to display details for.
     */
    public void showCampDetails(String campName){
        view = new PrintCampDetails(camps.get(campName).getCampName(), camps.get(campName).getDates(), camps.get(campName).getRegistrationEndDate(), camps.get(campName).getLocation(), camps.get(campName).getCampAccessbility(), camps.get(campName).getTotalSlots(), camps.get(campName).getCampCommitteeSlots(), camps.get(campName).getDescription(), camps.get(campName).getStaffInChargeID(), camps.get(campName).isVisibility(), camps.get(campName).getParticipants(), camps.get(campName).getCampCommittee(), camps.get(campName).getBlacklist());
        view.display();
    }

    /**
     * Checks if a user (student or staff) has any associated camps based on the provided parameters.
     *
     * @param userID      The ID of the user to check for associated camps.
     * @param isStudent   A boolean indicating if the user is a student or staff.
     * @param facultyName The faculty name used for filtering camps (unused in this method).
     * @return True if the user has associated camps, false otherwise.
     */
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

    /**
     * Retrieves the names of camps associated with a user (staff or student) based on the provided parameters.
     *
     * @param userID      The ID of the user to retrieve associated camp names.
     * @param isStudent   A boolean indicating if the user is a student or staff.
     * @param facultyName The faculty name used for filtering camps (unused in this method).
     * @return An ArrayList containing names of camps associated with the user.
     */
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


    /**
     * Retrieves the name of a camp based on the specified index, user ID, and user type (staff or student).
     *
     * @param idx       The index of the camp to retrieve.
     * @param userID    The ID of the user (staff or student).
     * @param isStudent A boolean indicating if the user is a student or staff.
     * @return The name of the camp at the specified index and associated with the given user, or null if not found.
     */
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

    /**
     * Renders the view by invoking the display method of the provided ICampView object.
     *
     * @param view The ICampView object to render.
     */
    private void render(ICampView view){
        view.display();
    }


    /**
     * Retrieves camps based on the user ID, user type, and faculty name using a FilterViewController instance.
     *
     * @param userID      The user ID for retrieving camps.
     * @param isStudent   A boolean indicating if the user is a student or staff.
     * @param facultyName The faculty name for filtering camps.
     * @return An ArrayList of camp names based on the filtering criteria.
     */
    public ArrayList<String> viewMyCamps(String userID, boolean isStudent, String facultyName) {

        FilterViewController filter = new FilterViewController(camps);
        return filter.viewMyCamps(userID, isStudent, facultyName);
    }


    /**
     * Retrieves a list of camp names filtered by start date based on the provided criteria.
     *
     * @param userID           The user ID for retrieving camps.
     * @param requireVisibility A boolean indicating if visibility is required for camps.
     * @param facultyName      The faculty name for filtering camps.
     * @param parsedDate       The start date for filtering camps.
     * @param viewAll          A boolean indicating if all camps should be viewed.
     * @return An ArrayList of camp names filtered by start date and the provided criteria.
     */
    public ArrayList<String> viewCampsFilterByStartDate(String userID, boolean requireVisibility, String facultyName,Date parsedDate, boolean viewAll) {

        FilterViewController filter = new FilterViewController(camps);
        return filter.viewCampsFilterByStartDate(userID, requireVisibility, facultyName, parsedDate, viewAll);

    }

    /**
     * Retrieves a list of camp names filtered by registration date based on the provided criteria.
     *
     * @param userID           The user ID for retrieving camps.
     * @param requireVisibility A boolean indicating if visibility is required for camps.
     * @param facultyName      The faculty name for filtering camps.
     * @param parsedDate       The registration date for filtering camps.
     * @param viewAll          A boolean indicating if all camps should be viewed.
     * @return An ArrayList of camp names filtered by registration date and the provided criteria.
     */
    public ArrayList<String> viewCampsFilterByRegDate(String userID, boolean requireVisibility, String facultyName, Date parsedDate, boolean viewAll) {
        FilterViewController filter = new FilterViewController(camps);
        return filter.viewCampsFilterByRegDate(userID, requireVisibility, facultyName, parsedDate, viewAll);
    }


    /**
     * Retrieves a list of camp names filtered by location based on the provided criteria.
     *
     * @param userID           The user ID for retrieving camps.
     * @param requireVisibility A boolean indicating if visibility is required for camps.
     * @param facultyName      The faculty name for filtering camps.
     * @param findLocation     The location to find for filtering camps.
     * @param viewAll          A boolean indicating if all camps should be viewed.
     * @return An ArrayList of camp names filtered by location and the provided criteria.
     */
    public ArrayList<String> viewCampsFilterByLocation(String userID, boolean requireVisibility, String facultyName, String findLocation, boolean viewAll) {
        FilterViewController filter = new FilterViewController(camps);
        return filter.viewCampsFilterByLocation(userID, requireVisibility, facultyName, findLocation, viewAll);
    }


    /**
     * Retrieves a list of camp names filtered by camp name based on the provided criteria.
     *
     * @param userID           The user ID for retrieving camps.
     * @param requireVisibility A boolean indicating if visibility is required for camps.
     * @param facultyName      The faculty name for filtering camps.
     * @param campName         The name of the camp for filtering camps.
     * @param viewAll          A boolean indicating if all camps should be viewed.
     * @return An ArrayList of camp names filtered by camp name and the provided criteria.
     */
    public ArrayList<String> viewCampsFilterByCampName(String userID, boolean requireVisibility, String facultyName, String campName, boolean viewAll) {
        FilterViewController filter = new FilterViewController(camps);
        return filter.viewCampsFilterByCampName(userID, requireVisibility, facultyName, campName, viewAll);
    }


    /**
     * Retrieves a list of all camps based on the provided criteria.
     *
     * @param userID           The user ID for retrieving camps.
     * @param requireVisibility A boolean indicating if visibility is required for camps.
     * @param facultyName      The faculty name for filtering camps.
     * @return An ArrayList of all camp names based on the provided criteria.
     */
    public ArrayList<String> viewAllCamps(String userID, boolean requireVisibility, String facultyName) {

        FilterViewController filter = new FilterViewController(camps);
        return filter.viewAllCamps(userID, requireVisibility, facultyName);

    }


    // ################
    // #  Validators  #
    // ################

    /**
     * Checks if there are any visible camps accessible to students based on faculty accessibility.
     *
     * @param userID      The user ID for checking camps.
     * @param facultyName The faculty name for filtering accessible camps.
     * @return True if there are visible camps accessible to students; false otherwise.
     */
    public boolean hasAnyCampsToViewForStudents(String userID, String facultyName){
        for (CampModel camp : camps.values()) {
            if (camp.isVisibility() && (camp.getCampAccessbility().equals(facultyName) || camp.getCampAccessbility().equals("NTU"))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a specific camp is full, considering all slots are completely filled up, including committee slots.
     *
     * @param campName The name of the camp to check for full capacity.
     * @return True if the camp is full; false otherwise.
     */
    // Full as in all slots are completely filled up including the committee slots
    public boolean isCampFull(String campName){
        return camps.get(campName).getTotalSlots() == (camps.get(campName).getCampCommitteeSlots() + camps.get(campName).getParticipants().size());
    }

    /**
     * Checks if there are no camps available.
     *
     * @return True if there are no camps; false if there are camps available.
     */
    public boolean isAllCampsEmpty(){
        return camps.isEmpty();
    }

    /**
     * Checks if the user is already part of the camp committee for another camp.
     *
     * @param userID The user ID to check for camp committee membership.
     * @return True if the user is part of the camp committee for another camp; false otherwise.
     */
    public boolean isAlreadyPartOfCampCommitteeOfAnotherCamp(String userID){
        for(CampModel camp : camps.values()){
            if(camp.getCampCommittee().contains(userID)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a date overlap for the user between the specified camp and another camp.
     *
     * @param campName The name of the camp to compare dates against.
     * @param userID   The user ID for checking date overlaps.
     * @return True if there is a date overlap between the specified camp and another camp the user is part of; false otherwise.
     */
    public boolean isCampDateOverlapping(String campName, String userID){
        Date[] dateArr = getCampDates(campName);
        Date[] dateArr2;

        for(CampModel camp : camps.values()){
            if(!camp.getCampName().equals(campName) && (camp.getParticipants().contains(userID) || camp.getCampCommittee().contains(userID))){

                // Get the dates of the second camp
                dateArr2 = camp.getDates();

                if(dateArr[0].before(dateArr2[0]) && dateArr[1].after(dateArr2[0]) ||
                        dateArr[0].before(dateArr2[1]) && dateArr[1].after(dateArr2[1]) ||
                        dateArr[0].before(dateArr2[0]) && dateArr[1].after(dateArr2[1]) ||
                        dateArr[0].after(dateArr2[0]) && dateArr[1].before(dateArr2[1]))
                    return true;

            }
        }
        return false;

    }

    // ########################
    // #  Accessor & Mutator  #
    // ########################

    /**
     * Retrieves the staff in charge ID for the specified camp.
     *
     * @param campName The name of the camp to retrieve the staff in charge ID from.
     * @return The staff in charge ID for the specified camp.
     */
    public String getStaffInChargeID(String campName){
        return camps.get(campName).getStaffInChargeID();
    }

    /**
     * Retrieves the camp name of the camp committee a user is part of.
     *
     * @param userID The user ID to search for in camp committees.
     * @return The camp name of the camp committee the user is part of; null if not found.
     */
    public String getCampNameOfCampCommittee(String userID){
        for(CampModel camp : camps.values()){
            if(camp.getCampCommittee().contains(userID)){
                return camp.getCampName();
            }
        }
        return null;
    }


    /**
     * Adds a participant to the specified camp.
     *
     * @param campName The name of the camp to add the participant to.
     * @param userID   The user ID of the participant to be added.
     */
    public void addParticipant(String campName, String userID){
        camps.get(campName).addParticipant(userID);
    }

    /**
     * Removes a participant from the specified camp.
     *
     * @param campName The name of the camp to remove the participant from.
     * @param userID   The user ID of the participant to be removed.
     */
    public void removeParticipant(String campName, String userID){
        camps.get(campName).removeParticipant(userID);
    }

    /**
     * Adds a user to the camp committee of a specified camp.
     *
     * @param campName The name of the camp to add the user to its committee.
     * @param userID   The user ID to be added to the camp committee.
     */
    public void addCampCommittee(String campName, String userID){
        camps.get(campName).addCampCommittee(userID);
    }

    /**
     * Creates and adds a new camp to the collection of camps.
     *
     * @param campName           The name of the new camp.
     * @param dates              An array of dates representing the camp duration.
     * @param registrationEndDate The registration end date for the camp.
     * @param location           The location of the camp.
     * @param accessibility      The accessibility of the camp.
     * @param totalSlots         The total available slots for participants in the camp.
     * @param campCommitteeSlots The available slots specifically for the camp committee.
     * @param description        The description of the camp.
     * @param userID             The user ID of the staff in charge of the camp.
     * @param visibility         The visibility status of the camp.
     */
    public void addCamp(String campName, Date[] dates, Date registrationEndDate, String location, String accessibility, int totalSlots, int campCommitteeSlots, String description, String userID, boolean visibility) {
        CampModel camp = new CampModel(campName, dates, registrationEndDate, location, accessibility, totalSlots, campCommitteeSlots, description, userID, visibility);
        camps.put(campName, camp);
    }


    /**
     * Adds a student to the blacklist of a specified camp.
     *
     * @param campName The name of the camp to add the student to its blacklist.
     * @param userID   The user ID of the student to be added to the blacklist.
     */
    public void addStudentToBlacklist(String campName, String userID){
        camps.get(campName).addStudBlacklist(userID);
    }

    /**
     * Checks if a camp name is already taken or exists in the collection of camps.
     *
     * @param campName The name of the camp to check for existence.
     * @return True if the camp name is already taken, otherwise false.
     */
    public boolean getIsCampNameTaken(String campName){
        return camps.containsKey(campName);
    }

    /**
     * Retrieves camp details for generating a report.
     *
     * @param campName The name of the camp to retrieve details for the report.
     * @return An ArrayList containing various details of the camp for reporting purposes.
     */
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
        campDetails.add(camps.get(campName).isVisibility());
        return campDetails;
    }

    /**
     * Retrieves the blacklist of participants for a specific camp.
     *
     * @param campID The ID of the camp to retrieve the blacklist for.
     * @return An ArrayList of participants in the blacklist for the specified camp.
     */
    public ArrayList<String> getCampBlacklist(String campID){
        return camps.get(campID).getBlacklist();
    }


    /**
     * Retrieves the visibility status of a specific camp.
     *
     * @param campID The ID of the camp to retrieve the visibility status for.
     * @return The visibility status of the specified camp.
     */
    public boolean getCampVisibility(String campID){
        return camps.get(campID).isVisibility();
    }


    /**
     * Retrieves the dates of a specific camp.
     *
     * @param campID The ID of the camp to retrieve the dates for.
     * @return An array of dates representing the start and end dates of the specified camp.
     */
    public Date[] getCampDates(String campID){
        return camps.get(campID).getDates();
    }

    /**
     * Retrieves the registration end date of a specific camp.
     *
     * @param campID The ID of the camp to retrieve the registration end date for.
     * @return The registration end date of the specified camp.
     */
    public Date getCampRegistrationEndDate(String campID){
        return camps.get(campID).getRegistrationEndDate();
    }

    /**
     * Retrieves the total slots available for a specific camp.
     *
     * @param campID The ID of the camp to retrieve the total slots for.
     * @return The total slots available in the specified camp.
     */
    public int getCampTotalSlots(String campID){
        return camps.get(campID).getTotalSlots();
    }

    /**
     * Retrieves the committee slots available for a specific camp.
     *
     * @param campID The ID of the camp to retrieve the committee slots for.
     * @return The number of committee slots available in the specified camp.
     */
    public int getCampCommitteeSlots(String campID){
        return camps.get(campID).getCampCommitteeSlots();
    }

    /**
     * Retrieves the participants of a specific camp.
     *
     * @param campID The ID of the camp to retrieve the participants for.
     * @return An ArrayList containing the participants of the specified camp.
     */
    public ArrayList<String> getCampParticipants(String campID){
        return camps.get(campID).getParticipants();
    }

    /**
     * Retrieves the committee members of a specific camp.
     *
     * @param campID The ID of the camp to retrieve the committee members for.
     * @return An ArrayList containing the committee members of the specified camp.
     */
    public ArrayList<String> getCampCommittee(String campID){
        return camps.get(campID).getCampCommittee();
    }

    /**
     * Sets the description of a specific camp.
     *
     * @param campID      The ID of the camp to set the description for.
     * @param description The new description to be set for the camp.
     */
    public void setCampDescription(String campID, String description){
        camps.get(campID).setDescription(description);
    }

    /**
     * Sets the location of a specific camp.
     *
     * @param campID   The ID of the camp to set the location for.
     * @param location The new location to be set for the camp.
     */
    public void setLocation(String campID, String location){
        camps.get(campID).setLocation(location);
    }

    /**
     * Sets the accessibility of a specific camp.
     *
     * @param campID       The ID of the camp to set the accessibility for.
     * @param accessbility The new accessibility to be set for the camp.
     */
    public void setCampAccessibility(String campID, String accessbility){
        camps.get(campID).setCampAccessbility(accessbility);
    }

    /**
     * Sets the visibility of a specific camp.
     *
     * @param campID    The ID of the camp to set the visibility for.
     * @param visibility The new visibility status to be set for the camp.
     */
    public void setCampVisibility(String campID, boolean visibility){
        camps.get(campID).setVisibility(visibility);
    }

    /**
     * Sets the start date of a specific camp.
     *
     * @param campID    The ID of the camp to set the start date for.
     * @param startDate The new start date to be set for the camp.
     */
    public void setStartDate(String campID, Date startDate){
        camps.get(campID).setDates(new Date[]{startDate, camps.get(campID).getDates()[1]});
    }

    /**
     * Sets the end date of a specific camp.
     *
     * @param campID  The ID of the camp to set the end date for.
     * @param endDate The new end date to be set for the camp.
     */
    public void setEndDate(String campID, Date endDate){
        camps.get(campID).setDates(new Date[]{camps.get(campID).getDates()[0], endDate});
    }

    /**
     * Sets the registration end date of a specific camp.
     *
     * @param campID             The ID of the camp to set the registration end date for.
     * @param registrationEndDate The new registration end date to be set for the camp.
     */
    public void setRegistrationEndDate(String campID, Date registrationEndDate){
        camps.get(campID).setRegistrationEndDate(registrationEndDate);
    }

    /**
     * Sets the total slots of a specific camp.
     *
     * @param campID     The ID of the camp to set the total slots for.
     * @param totalSlots The new total slots to be set for the camp.
     */
    public void setCampTotalSlots(String campID, int totalSlots){
        camps.get(campID).setTotalSlots(totalSlots);
    }

    /**
     * Sets the committee slots of a specific camp.
     *
     * @param campID         The ID of the camp to set the committee slots for.
     * @param committeeSlots The new committee slots to be set for the camp.
     */
    public void setCampCommitteeSlots(String campID, int committeeSlots){
        camps.get(campID).setCampCommitteeSlots(committeeSlots);
    }


}


