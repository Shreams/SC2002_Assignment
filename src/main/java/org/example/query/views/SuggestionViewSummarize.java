package org.example.query.views;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryView;

/**
 * @author Group1
 * @version 1.0
 * 
 * 
 * The `SuggestionViewSummarize` class is an implementation of the `IQueryView` interface
 * designed to display summarized information about a suggestion query.
 * It includes details such as the suggestion text, status, and a unique identifier.
 */
public class SuggestionViewSummarize implements IQueryView {

    private String enquiry;
    private QueryStatus status;

    private String uid;

    /**
     * Constructs a new `SuggestionViewSummarize` object with the specified parameters.
     *
     * @param enquiry The text of the suggestion.
     * @param status The status of the suggestion (PENDING, APPROVED, or DENIED).
     * @param uid The unique identifier for the suggestion.
     */
    public SuggestionViewSummarize(String enquiry, QueryStatus status, String uid) {
        this.enquiry = enquiry;
        this.status = status;
        this.uid = uid;
    }
    
    /**
     * Displays the summarized information about the suggestion, including its text,
     * status, and unique identifier.
     */
    @Override
    public void display() {

        System.out.println("Suggestion: " + this.enquiry);
        switch (this.status) {
            case PENDING -> System.out.println("Status: PENDING");
            case APPROVED -> System.out.println("Status: ANSWERED");
            case DENIED -> System.out.println("Status: REJECTED");
        }
        System.out.println("Suggestion ID: " + this.uid);
    }
}
