package org.example.query.views;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryView;

/**
 * @author Group1
 * @version 1.0
 * 
 * 
 * The `EnquiryViewSummarize` class represents a view for summarizing an enquiry with its status and unique ID.
 * It implements the `IQueryView` interface for displaying information about an enquiry.
 */
public class EnquiryViewSummarize implements IQueryView {


    private String enquiry;
    private QueryStatus status;

    private String uid;

    /**
     * Constructor for creating an instance of the `EnquiryViewSummarize` class.
     *
     * @param enquiry The text of the enquiry.
     * @param status  The status of the enquiry (PENDING or APPROVED).
     * @param uid     The unique ID associated with the enquiry.
     */
    public EnquiryViewSummarize(String enquiry, QueryStatus status, String uid) {
        this.enquiry = enquiry;
        this.status = status;
        this.uid = uid;
    }



    @Override
    public void display() {

        System.out.println("Enquiry: " + this.enquiry);
        switch (this.status) {
            case PENDING -> System.out.println("Status: PENDING");
            case APPROVED -> System.out.println("Status: ANSWERED");
        }
        System.out.println("Enquiry ID: " + this.uid);


    }
}
