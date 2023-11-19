package org.example.query.enquiries;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryController;
import org.example.query.interfaces.IQueryView;
import org.example.query.views.EnquiryView;
import org.example.query.views.EnquiryViewSummarize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EnquiryController implements IQueryController {

    private HashMap<String, EnquiryModel> enquiries;


    public EnquiryController() {
        enquiries = new HashMap<String, EnquiryModel>();
    }

    // #####################
    // #  Query Modifiers  #
    // #####################

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

    @Override
    public void editQuery (String userID, String queryID){
        if (enquiries.get(queryID).getAuthor().equals(userID) && enquiries.get(queryID).getStatus() == QueryStatus.PENDING) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("*********** Edit Enquiry ***********");
            System.out.println("Enter your new enquiry: ");
            String enquiry = scanner.nextLine();
            enquiries.get(queryID).setQuery(enquiry);
            System.out.println("Your enquiry has been edited successfully!");
        } else {
            System.out.println("You are not the author of the enquiry or the enquiry has been answered!");
        }
    }

    @Override
    public void deleteQuery (String userID, String queryID){
        if (enquiries.get(queryID).getAuthor().equals(userID) && enquiries.get(queryID).getStatus() == QueryStatus.PENDING) {
            enquiries.remove(queryID);
            System.out.println("Your enquiry has been deleted successfully!");
        } else {
            System.out.println("You are not the author of the enquiry or the enquiry has been answered!");
        }
    }



    // ####################
    // #    Views         #
    // ####################

    @Override
    public void showView (IQueryView view){
        view.display();
    }

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

    @Override
    public void showQueryDetails (String queryID){
        EnquiryModel model = enquiries.get(queryID);
        EnquiryView view = new EnquiryView(model.getID(), model.getAuthor(), model.getCampID(), model.getStatus(), model.getRespondent(), model.getQuery(), model.getResponse());
        showView(view);
    }


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

    @Override
    public boolean isQueryExist(String queryID) {
        return enquiries.containsKey(queryID);
    }

    @Override
    public boolean hasAnyQuery (String userID){
        for (String key : enquiries.keySet()) {
            if (enquiries.get(key).getAuthor().equals(userID)) {
                return true;
            }
        }
        return false;
    }



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

