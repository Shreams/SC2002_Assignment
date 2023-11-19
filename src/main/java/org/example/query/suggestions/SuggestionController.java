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

public class SuggestionController implements IQueryController {

    private HashMap<String, SuggestionModel> suggestions;

    public SuggestionController() {
        suggestions = new HashMap<String, SuggestionModel>();
    }


    // #####################
    // #  Query Modifiers  #
    // #####################
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

    @Override
    public void editQuery (String userID, String queryID){
        // Check if userID is the author of the query and if the query is still pending
        // If yes, edit the query

        if (suggestions.get(queryID).getAuthor().equals(userID) && suggestions.get(queryID).getStatus() == QueryStatus.PENDING) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("*********** Edit Suggestion ***********");
            System.out.println("Enter your new suggestion: ");
            String suggestion = scanner.nextLine();
            suggestions.get(queryID).setQuery(suggestion);
            System.out.println("Your suggestion has been edited successfully!");
        } else {
            System.out.println("You are not the author of the suggestion or the suggestion has been answered!");
        }
    }

    @Override
    public void deleteQuery (String userID, String queryID){
        // Check if userID is the author of the query and if the query is still pending
        // If yes, delete the query

        if (suggestions.get(queryID).getAuthor().equals(userID) && suggestions.get(queryID).getStatus() == QueryStatus.PENDING) {
            suggestions.remove(queryID);
            System.out.println("Your suggestion has been deleted successfully!");
        } else {
            System.out.println("You are not the author of the suggestion or the suggestion has been answered!");
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
    public void showQueryDetails (String queryID){

        SuggestionModel suggestion = suggestions.get(queryID);
        SuggestionView view = new SuggestionView(suggestion.getID(), suggestion.getAuthor(), suggestion.getQuery(), suggestion.getStatus(), suggestion.getCampID(), suggestion.getRespondent());
        showView(view);
    }

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

    @Override
    public boolean isQueryExist (String queryID){
        return suggestions.containsKey(queryID);
    }

    @Override
    public boolean hasAnyQuery (String userID){
        for (String key : suggestions.keySet()) {
            if (suggestions.get(key).getAuthor().equals(userID)) {
                return true;
            }
        }
        return false;
    }


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

    public String getAuthor(String queryID){
        return suggestions.get(queryID).getAuthor();
    }

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

