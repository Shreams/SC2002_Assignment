package org.example.query.views;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryView;

/**
 * @author Group1
 * @version 1.0
 * 
 * 
 * This class represents a view for displaying details of an enquiry.
 * It implements the {@link IQueryView} interface.
 */
public class EnquiryView implements IQueryView {

    private String author;
    private String ID;
    private String enquiry;
    private QueryStatus status;
    private String response;

    private String respondent;

    private String campID;
    
    /**
     * Constructs an EnquiryView object with the provided parameters.
     *
     * @param uid        The unique ID of the enquiry.
     * @param author     The author of the enquiry.
     * @param campID     The camp ID associated with the enquiry.
     * @param status     The status of the enquiry (PENDING or APPROVED).
     * @param respondent The respondent to the enquiry.
     * @param enquiry    The text of the enquiry.
     * @param response   The response to the enquiry.
     */
    public EnquiryView(String uid, String author, String campID, QueryStatus status, String respondent, String enquiry, String response) {
        this.author = author;
        this.enquiry = enquiry;
        this.ID = uid;
        this.status = status;
        this.respondent = respondent;
        this.response = response;
        this.campID = campID;
    }

    /**
     * Displays the details of the enquiry in a formatted manner.
     */
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
