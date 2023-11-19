package org.example.query.views;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryView;

public class SuggestionViewSummarize implements IQueryView {

    private String enquiry;
    private QueryStatus status;

    private String uid;

    public SuggestionViewSummarize(String enquiry, QueryStatus status, String uid) {
        this.enquiry = enquiry;
        this.status = status;
        this.uid = uid;
    }
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
