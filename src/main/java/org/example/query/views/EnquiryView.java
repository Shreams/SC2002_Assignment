package org.example.query.views;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryView;

public class EnquiryView implements IQueryView {

    private String author;
    private String ID;
    private String enquiry;
    private QueryStatus status;
    private String response;

    private String respondent;

    private String campID;
    public EnquiryView(String uid, String author, String campID, QueryStatus status, String respondent, String enquiry, String response) {
        this.author = author;
        this.enquiry = enquiry;
        this.ID = uid;
        this.status = status;
        this.respondent = respondent;
        this.response = response;
        this.campID = campID;
    }

    @Override
    public void display() {
        System.out.println("\n~~Enquiry Details~~");
        System.out.println("Enquiry ID: " + this.ID);
        System.out.println("Author: " + this.author);
        System.out.println("Camp Name: " + this.campID);

        switch (this.status) {
            case PENDING -> System.out.println("Status: PENDING");
            case APPROVED -> System.out.println("Status: ANSWERED");
        }

        System.out.println("Enquiry: " + this.enquiry);
        System.out.println("Response: " + this.response);
        System.out.println("Respondent: " + this.respondent);

    }
}
