package org.example.query.suggestions;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryController;
import org.example.query.interfaces.IQueryView;
import org.example.query.views.SuggestionView;
import org.example.query.views.SuggestionViewSummarize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author Group1
 * @version 1.0
 * 
 * The SuggestionController class is responsible for managing and controlling suggestions.
 * It allows users to create, edit, reply to, and view suggestions, among other actions.
 */
public class SuggestionController implements IQueryController {

	 /**
     * A HashMap to store suggestions, where the key is the suggestion ID and the value is the suggestion model.
     */
    private HashMap<String, SuggestionModel> suggestions;

    
    /**
     * Constructs a new SuggestionController instance.
     * Initializes the suggestions HashMap.
     */
    public SuggestionController() {
        suggestions = new HashMap<String, SuggestionModel>();
    }


    // #####################
    // #  Query Modifiers  #
    // #####################
    
    /**
     * Reply to a suggestion identified by the given query ID and associate it with a respondent.
     *
     * @param queryID      The unique ID of the suggestion being replied to.
     * @param respondentID The unique ID of the user providing the reply.
     * @return True if the reply was successful, false otherwise.
     */
    @Override
    public boolean replyToQuery(String queryID, String respondentID) {

        // Reply by setting the status
        // If the author and respondent are the same person, not allowed
        if (suggestions.get(queryID).getAuthor().equals(respondentID)) {
            System.out.println("You are not allowed to reply to your own suggestion!");
            return false;
        }

        // If the query is not pending, not allowed
        if (suggestions.get(queryID).getStatus() != QueryStatus.PENDING) {
            System.out.println("You are not allowed to reply to this suggestion!");
            return false;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("*********** Reply to Suggestion ***********");
                System.out.println("Enter your choice: ");
                System.out.println("1. APPROVE");
                System.out.println("2. REJECT");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        suggestions.get(queryID).setStatus(QueryStatus.APPROVED);
                    }
                    case 2 -> {
                        suggestions.get(queryID).setStatus(QueryStatus.DENIED);
                    }
                }
                suggestions.get(queryID).setRespondent(respondentID);
                System.out.println("Your reply has been sent successfully!");
                return true;
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
    }

    /**
     * Provide approval or denial to a suggestion identified by the given query ID.
     *
     * @param queryID      The ID of the suggestion being approved or denied.
     * @param respondentID The ID of the user providing the approval or denial.
     * @param status       The status to set for the suggestion (APPROVED or DENIED).
     * @return True if the approval or denial was successful, false otherwise.
     */
    public boolean approvalToQuery(String queryID, String respondentID, QueryStatus status){
        if (suggestions.get(queryID).getAuthor().equals(respondentID)) {
            System.out.println("You are not allowed to reply to your own suggestion!");
            return false;
        }

        if (suggestions.get(queryID).getStatus() != QueryStatus.PENDING) {
            System.out.println("You are not allowed to reply to this suggestion!");
            return false;
        }

        suggestions.get(queryID).setStatus(status);
        suggestions.get(queryID).setRespondent(respondentID);
        System.out.println("Your reply has been sent successfully!");

        return true;
    }


    @Override
    // From the outside i assume i will grab the userID, campID and query and the campController
    // Camp controller will help me to check if the user is a student and not camp committee
    /**
     * Create a new suggestion for a user identified by their userID and associated with a camp.
     *
     * @param userID  The ID of the user creating the suggestion.
     * @param campID  The ID of the camp associated with the suggestion.
     */
    public void createQuery(String userID, String campID) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("*********** Suggest Changes made ***********");
                System.out.println("Enter your Suggestions: ");
                String enquiry = scanner.nextLine();

                String uid = String.valueOf(UUID.randomUUID());
                suggestions.put(uid, new SuggestionModel(uid, userID, campID, enquiry));
                System.out.println("Your suggestion has been sent successfully!");
                break;
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
    }

    
    /**
     * Edits a query suggestion.
     *
     * @param userID  The ID of the user editing the suggestion.
     * @param queryID The ID of the query to be edited.
     */
    @Override
    public void editQuery (String userID, String queryID){
        // Check if userID is the author of the query and if the query is still pending
        // If yes, edit the query

        if(!suggestions.get(queryID).getAuthor().equals(userID)){
            System.out.println("You are not the author of the enquiry or ");
            return;
        }

        if (suggestions.get(queryID).getAuthor().equals(userID) && suggestions.get(queryID).getStatus() == QueryStatus.PENDING) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("*********** Edit Suggestion ***********");
            System.out.println("Enter your new suggestion: ");
            String suggestion = scanner.nextLine();
            suggestions.get(queryID).setQuery(suggestion);
            System.out.println("Your suggestion has been edited successfully!");
        } else {
            System.out.println("The suggestion has been answered!");
        }
    }

    /**
     * Deletes a query suggestion.
     *
     * @param userID  The ID of the user deleting the suggestion.
     * @param queryID The ID of the query to be deleted.
     */
    @Override
    public void deleteQuery (String userID, String queryID){
        // Check if userID is the author of the query and if the query is still pending
        // If yes, delete the query

        if(!suggestions.get(queryID).getAuthor().equals(userID)){
            System.out.println("You are not the author of the enquiry or ");
            return;
        }

        if (suggestions.get(queryID).getAuthor().equals(userID) && suggestions.get(queryID).getStatus() == QueryStatus.PENDING) {
            suggestions.remove(queryID);
            System.out.println("Your suggestion has been deleted successfully!");
        } else {
            System.out.println("The suggestion has been answered!");
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
     * Displays details of a specific query.
     *
     * @param queryID The ID of the query to be displayed.
     */
    @Override
    public void showQueryDetails (String queryID){

        SuggestionModel suggestion = suggestions.get(queryID);
        SuggestionView view = new SuggestionView(suggestion.getID(), suggestion.getAuthor(), suggestion.getQuery(), suggestion.getStatus(), suggestion.getCampID(), suggestion.getRespondent());
        showView(view);
    }

    /**
     * Displays all queries associated with a given camp.
     *
     * @param campID The ID of the camp for which queries should be displayed.
     */
    @Override
    public void viewAllQueryOfThatCamp (String campID){
        int idx = 1;
        System.out.println("####################################");
        System.out.println("##    All Suggestions             ##");
        System.out.println("####################################");
        System.out.println("Showing enquiries for camp: " + campID);
        System.out.println();

        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getCampID().equals(campID)) {
                System.out.println(idx + ". " + suggestions.get(key).getQuery());
                idx++;
                (new SuggestionViewSummarize(suggestions.get(key).getQuery(), suggestions.get(key).getStatus(), suggestions.get(key).getID())).display();
            }
        }
    }

    /**
     * Displays all queries authored by a specific user.
     *
     * @param userID The ID of the user whose queries should be displayed.
     */
    @Override
    public void viewMyQuery (String userID){

        int idx = 1;
        System.out.println("#################################");
        System.out.println("##     My Suggestions          ##");
        System.out.println("#################################");
        System.out.println();


        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getAuthor().equals(userID)) {
                System.out.println(idx + ". " + suggestions.get(key).getQuery());
                idx++;
                (new SuggestionViewSummarize(suggestions.get(key).getQuery(), suggestions.get(key).getStatus(), suggestions.get(key).getID())).display();
            }
        }
    }


    // ################
    // #  Validators  #
    // ################

    /**
     * Check if a suggestion with the given query ID exists.
     *
     * @param queryID The unique ID of the suggestion to check for existence.
     * @return True if the suggestion exists, false otherwise.
     */
    @Override
    public boolean isQueryExist (String queryID){
        return suggestions.containsKey(queryID);
    }

    /**
     * Check if a user identified by their userID has any suggestions.
     *
     * @param userID The unique ID of the user to check for suggestions.
     * @return True if the user has any suggestions, false otherwise.
     */
    @Override
    public boolean hasAnyQuery (String userID){
        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getAuthor().equals(userID)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Check if there are any suggestions associated with a camp identified by its campID.
     *
     * @param campID The unique ID of the camp to check for suggestions.
     * @return True if there are suggestions for the camp, false otherwise.
     */
    @Override
    public boolean hasAnyQueryOfThatCamp (String campID){
        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getCampID().equals(campID)) {
                return true;
            }
        }
        return false;
    }



    // ########################
    // #  Accessor & Mutator  #
    // ########################

    /**
     * Retrieves the author of a suggestion identified by the given queryID.
     *
     * @param queryID The unique identifier of the suggestion.
     * @return The author of the suggestion.
     */
    public String getAuthor(String queryID){
        return suggestions.get(queryID).getAuthor();
    }

    /**
     * Retrieves a list of query IDs associated with a specific user.
     *
     * @param userID The unique identifier of the user.
     * @return An ArrayList containing query IDs authored by the user.
     */
    @Override
    public ArrayList<String> getAllQueryByUserID (String userID){

        ArrayList<String> suggestionID = new ArrayList<String>();
        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getAuthor().equals(userID)) {
                suggestionID.add(key);
            }
        }
        return suggestionID;
    }

    /**
     * Retrieves a list of query IDs associated with a specific campaign.
     *
     * @param campID The unique identifier of the campaign.
     * @return An ArrayList containing query IDs associated with the campaign.
     */
    @Override
    public ArrayList<String> getAllQueryByCampID (String campID){

        ArrayList<String> suggestionID = new ArrayList<String>();
        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getCampID().equals(campID)) {
                suggestionID.add(key);
            }
        }
        return suggestionID;
    }

    /**
     * Retrieves the query ID at a specific index for a given user.
     *
     * @param idx    The index of the query to retrieve (1-based).
     * @param userID The unique identifier of the user.
     * @return The query ID at the specified index for the user, or null if not found.
     */
    @Override
    public String getMyQueryFromIdx ( int idx, String userID){
        int i = 1;
        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getAuthor().equals(userID)) {
                if (i == idx) {
                    return key;
                }
                i++;
            }
        }
        return null;
    }

    /**
     * Retrieves the query ID at a specific index for a given campaign.
     *
     * @param idx    The index of the query to retrieve (1-based).
     * @param campID The unique identifier of the campaign.
     * @return The query ID at the specified index for the campaign, or null if not found.
     */
    @Override
    public String getCampQueryFromIdx ( int idx, String campID) {
        int i = 1;
        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getCampID().equals(campID)) {
                if (i == idx) {
                    return key;
                }
                i++;
            }
        }
        return null;
    }

}

