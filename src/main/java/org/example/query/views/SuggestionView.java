package org.example.query.views;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryView;

public class SuggestionView implements IQueryView {

    private  String author;
    private String ID;
    private String suggestion;
    private QueryStatus status;

    private String campID;

    private String respondent;

    public SuggestionView(String uid, String author, String suggestion, QueryStatus status, String campID, String respondent) {
        this.author = author;
        this.suggestion = suggestion;
        this.ID = uid;
        this.status = status;
        this.campID = campID;
        this.respondent = respondent;
    }

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


