package org.example.query.enquiries;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryController;
import org.example.query.interfaces.IQueryView;
import org.example.query.views.EnquiryView;
import org.example.query.views.EnquiryViewSummarize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Group1
 * @version 1.0
 * 
 * The EnquiryController class is responsible for managing and controlling inquiries related to camps.
 * It allows users to create, reply to, edit, and delete inquiries, as well as view and interact with them.
 * The class also provides various query-related functionalities and validations.
 */
public class EnquiryController implements IQueryController {
	 /**
     * A HashMap to store and manage inquiries, where the keys are query IDs and the values are EnquiryModel objects.
     */
    private HashMap<String, EnquiryModel> enquiries;


    /**
     * Constructs a new EnquiryController with an empty HashMap for inquiries.
     */
    public EnquiryController() {
        enquiries = new HashMap<String, EnquiryModel>();
    }

    // #####################
    // #  Query Modifiers  #
    // #####################

    /**
     * Allows a user to reply to a specific query.
     *
     * @param queryID      The ID of the query to which the user wants to reply.
     * @param respondentID The ID of the user responding to the query.
     * @return true if the reply was successful, false otherwise.
     */
    @Override
    public boolean replyToQuery(String queryID, String respondentID) {

        // If the author and respondent are the same person, not allowed
        if (enquiries.get(queryID).getAuthor().equals(respondentID)) {
            System.out.println("You are not allowed to reply to your own enquiry!");
            return false;
        }

        // If the query is not pending, not allowed
        if (enquiries.get(queryID).getStatus() != QueryStatus.PENDING) {
            System.out.println("You are not allowed to reply to this enquiry!");
            return false;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("*********** Reply to Enquiry ***********");
                System.out.println("Enter your reply: ");
                String response = scanner.nextLine();
                enquiries.get(queryID).setResponse(response);
                enquiries.get(queryID).setRespondent(respondentID);
                enquiries.get(queryID).setStatus(QueryStatus.APPROVED);
                System.out.println("Your reply has been sent successfully!");
                return true;
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }

    }

    /**
     * Creates a new inquiry regarding a camp.
     *
     * @param userID  The ID of the user creating the inquiry.
     * @param campID  The ID of the camp associated with the inquiry.
     */
    @Override
    public void createQuery(String userID, String campID) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("*********** Enquire about Camp ***********");
                System.out.println("Enter your enquiry: ");
                String enquiry = scanner.nextLine();
                String uid = String.valueOf(userID);
                enquiries.put(uid, new EnquiryModel(uid, userID, campID, enquiry));
                System.out.println("Your enquiry has been sent successfully!");
                break;
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
    }

    /**
     * Edits an existing inquiry if the user is the author and the inquiry is pending.
     *
     * @param userID   The ID of the user editing the inquiry.
     * @param queryID  The ID of the inquiry to be edited.
     */
    @Override
    public void editQuery (String userID, String queryID){

        if(!enquiries.get(queryID).getAuthor().equals(userID)){
            System.out.println("You are not the author of the enquiry or ");
            return;
        }

        if (enquiries.get(queryID).getAuthor().equals(userID) && enquiries.get(queryID).getStatus() == QueryStatus.PENDING) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("*********** Edit Enquiry ***********");
            System.out.println("Enter your new enquiry: ");
            String enquiry = scanner.nextLine();
            enquiries.get(queryID).setQuery(enquiry);
            System.out.println("Your enquiry has been edited successfully!");
        } else {
            System.out.println("The enquiry has been answered!");
        }
    }

    /**
     * Deletes an existing inquiry if the user is the author and the inquiry is pending.
     *
     * @param userID   The ID of the user deleting the enquiry.
     * @param queryID  The ID of the enquiry to be deleted.
     * Cannot be deleted if enquiry is answered
     */
    @Override
    public void deleteQuery (String userID, String queryID){

        if(!enquiries.get(queryID).getAuthor().equals(userID)){
            System.out.println("You are not the author of the enquiry or ");
            return;
        }

        if (enquiries.get(queryID).getAuthor().equals(userID) && enquiries.get(queryID).getStatus() == QueryStatus.PENDING) {
            enquiries.remove(queryID);
            System.out.println("Your enquiry has been deleted successfully!");
        } else {
            System.out.println("The enquiry has been answered!");
        }
    }



    // ####################
    // #    Views         #
    // ####################

    /**
     * Displays a given query view.
     *
     * @param view The query view to be displayed.
     */
    @Override
    public void showView (IQueryView view){
        view.display();
    }

    /**
     * Displays all inquiries associated with a specific camp.
     *
     * @param campID The ID of the camp for which inquiries should be displayed.
     */
    @Override
    public void viewAllQueryOfThatCamp (String campID){
        int idx = 1;
        System.out.println("######################################");
        System.out.println("##     All Enquiries               ##");
        System.out.println("#####################################");
        System.out.println("Showing enquiries for camp: " + campID);
        System.out.println();

        for (EnquiryModel model : enquiries.values()) {
            if (model.getCampID().equals(campID)) {
                System.out.print(idx + ".");
                idx++;
                (new EnquiryViewSummarize(model.getQuery(), model.getStatus(), model.getID())).display();
            }
        }
    }

    /**
     * Displays detailed information about a specific inquiry.
     *
     * @param queryID The ID of the inquiry to be displayed.
     */
    @Override
    public void showQueryDetails (String queryID){
        EnquiryModel model = enquiries.get(queryID);
        EnquiryView view = new EnquiryView(model.getID(), model.getAuthor(), model.getCampID(), model.getStatus(), model.getRespondent(), model.getQuery(), model.getResponse());
        showView(view);
    }


    /**
     * Displays all inquiries created by a specific user.
     *
     * @param userID The ID of the user for whom inquiries should be displayed.
     */
    @Override
    public void viewMyQuery (String userID){
        int idx = 1;
        System.out.println("#################################");
        System.out.println("##     My Enquiry              ##");
        System.out.println("#################################");
        System.out.println();


        // Print out no query if there is no query
        if (!hasAnyQuery(userID)) {
            System.out.println("You have no query!");
            return;
        }

        for (EnquiryModel model : enquiries.values()) {
            if (model.getAuthor().equals(userID)) {
                System.out.print(idx + ".");
                idx++;
                (new EnquiryViewSummarize(model.getQuery(), model.getStatus(), model.getID())).display();
            }
        }
    }

    // ################
    // #  Validators  #
    // ################

    /**
     * Checks if an inquiry with a given ID exists.
     *
     * @param queryID The ID of the inquiry to be checked.
     * @return true if the inquiry exists, false otherwise.
     */
    @Override
    public boolean isQueryExist(String queryID) {
        return enquiries.containsKey(queryID);
    }

    /**
     * Checks if a user has any inquiries.
     *
     * @param userID The ID of the user to check for inquiries.
     * @return true if the user has inquiries, false otherwise.
     */
    @Override
    public boolean hasAnyQuery (String userID){
        for (String key : enquiries.keySet()) {
            if (enquiries.get(key).getAuthor().equals(userID)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Checks if there are any inquiries associated with a specific camp.
     *
     * @param campID The ID of the camp to check for inquiries.
     * @return true if there are inquiries for the camp, false otherwise.
     */
    @Override
    public boolean hasAnyQueryOfThatCamp (String campID){
        for (String key : enquiries.keySet()) {
            if (enquiries.get(key).getCampID().equals(campID)) {
                return true;
            }
        }
        return false;
    }

    // ########################
    // #  Accessor & Mutator  #
    // ########################

    /**
     * Gets the ID of the user's inquiry from an index.
     *
     * @param idx    The index of the inquiry to retrieve.
     * @param userID The ID of the user whose inquiries are being accessed.
     * @return The ID of the inquiry or null if not found.
     */
    @Override
    public String getMyQueryFromIdx ( int idx, String userID){
        int i = 1;
        for (String key : enquiries.keySet()) {
            if (enquiries.get(key).getAuthor().equals(userID)) {
                if (i == idx) {
                    showQueryDetails(key);
                    return key;
                }
                i++;
            }
        }
        return null;
    }



    /**
     * Gets the ID of a camp's inquiry at a specific index.
     *
     * @param idx    The index of the inquiry to retrieve.
     * @param campID The ID of the camp whose inquiries are being accessed.
     * @return The ID of the inquiry or null if not found.
     */
    @Override
    public String getCampQueryFromIdx ( int idx, String campID){
        int i = 1;
        for (String key : enquiries.keySet()) {
            if (enquiries.get(key).getCampID().equals(campID)) {
                if (i == idx) {
                    showQueryDetails(key);
                    return key;
                }
                i++;
            }
        }
        return null;
    }

    /**
     * Gets all inquiry IDs created by a specific user.
     *
     * @param userID The ID of the user whose inquiry IDs are being retrieved.
     * @return A list of inquiry IDs associated with the user.
     */
    @Override
    public ArrayList<String> getAllQueryByUserID (String userID){
        // Grab all the enquiries by the userID
        // return the enquiries id
        ArrayList<String> enquiriesID = new ArrayList<String>();
        for (String key : enquiries.keySet()) {
            if (enquiries.get(key).getAuthor().equals(userID)) {
                enquiriesID.add(key);
            }
        }
        return enquiriesID;
    }


    /**
     * Gets all inquiry IDs associated with a specific camp.
     *
     * @param campID The ID of the camp whose inquiry IDs are being retrieved.
     * @return A list of inquiry IDs associated with the camp.
     */
    @Override
    public ArrayList<String> getAllQueryByCampID (String campID){
        // Grab all the enquiries by the campID
        // return the enquiries id
        ArrayList<String> enquiriesID = new ArrayList<String>();
        for (String key : enquiries.keySet()) {
            if (enquiries.get(key).getCampID().equals(campID)) {
                enquiriesID.add(key);
            }
        }
        return enquiriesID;
    }
}

