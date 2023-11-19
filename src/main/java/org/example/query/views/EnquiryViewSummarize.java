package org.example.query.views;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryView;

public class EnquiryViewSummarize implements IQueryView {


    private String enquiry;
    private QueryStatus status;

    private String uid;

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
