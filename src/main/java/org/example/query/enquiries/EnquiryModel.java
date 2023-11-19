package org.example.query.enquiries;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryModel;

import java.util.UUID;

public class EnquiryModel implements IQueryModel {
    private String author;
    private String ID;
    private String enquiry;
    private QueryStatus status;
    private String response;

    private String respondent;

    private String campID;

    public EnquiryModel(String uid, String author, String campID, String enquiry) {
        this.author = author;
        this.enquiry = enquiry;
        this.ID = uid;
        this.status = QueryStatus.PENDING; // PENDING is the same as unanswered, ACCEPTED is the same as answered
        this.campID = campID;
    }


    @Override
    public QueryStatus getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(QueryStatus status) {
        this.status = status;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String getID() {
        return this.ID.toString();
    }


    @Override
    public String getCampID() {
        return campID;
    }

    @Override
    public void setCampID(String campID) {
        this.campID = campID;
    }

    @Override
    public String getQuery() {
        return this.enquiry;
    }

    @Override
    public void setQuery(String query) {
        this.enquiry = query;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }

    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }

    public String getRespondent() {
        return this.respondent;
    }
}
