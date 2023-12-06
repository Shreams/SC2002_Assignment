package org.example.query.views;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryView;

/**
 * @author Group1
 * @version 1.0
 * 
 * Represents a view of a suggestion in a query system.
 * This class implements the IQueryView interface and is used to display information
 * about a suggestion, including its author, status, camp name, suggestion text, and respondent.
 */
public class SuggestionView implements IQueryView {

    private  String author;
    private String ID;
    private String suggestion;
    private QueryStatus status;

    private String campID;

    private String respondent;

    /**
     * Initializes a new SuggestionView object with the provided parameters.
     *
     * @param uid       The unique ID of the suggestion.
     * @param author    The author of the suggestion.
     * @param suggestion The suggestion text.
     * @param status    The status of the suggestion (PENDING, APPROVED, or DENIED).
     * @param campID    The ID of the camp associated with the suggestion.
     * @param respondent The respondent to the suggestion.
     */
    public SuggestionView(String uid, String author, String suggestion, QueryStatus status, String campID, String respondent) {
        this.author = author;
        this.suggestion = suggestion;
        this.ID = uid;
        this.status = status;
        this.campID = campID;
        this.respondent = respondent;
    }

    /**
     * Displays the information about the suggestion, including its ID, author, status, camp name,
     * suggestion text, and respondent.
     */
    @Override
    public void display() {
        System.out.println("Suggestion ID: " + this.ID);
        System.out.println("Author: " + this.author);
        System.out.println("Camp Name: " + this.campID);

        switch (this.status) {
            case PENDING -> System.out.println("Status: PENDING");
            case APPROVED -> System.out.println("Status: ANSWERED");
            case DENIED -> System.out.println("Status: REJECTED");
        }

        System.out.println("Suggestion: " + this.suggestion);
        System.out.println("Respondent: " + this.respondent);

    }
}


