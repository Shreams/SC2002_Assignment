package org.example.query.enquiries;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryModel;

import java.util.UUID;
/**
 *@author Group1
 *@version 1.0
 *
 * The EnquiryModel class represents a model for handling enquiries.
 * It implements the IQueryModel interface, providing methods to manage query details.
 */
public class EnquiryModel implements IQueryModel {

    private String author;
    private String ID;
    private String enquiry;
    private QueryStatus status;
    private String response;

    private String respondent;

    private String campID;
    
    /**
     * Constructs an EnquiryModel object with the given parameters.
     *
     * @param uid     The unique identifier for the enquiry.
     * @param author  The author of the enquiry.
     * @param campID  The camp ID associated with the enquiry.
     * @param enquiry The text of the enquiry.
     */
    public EnquiryModel(String uid, String author, String campID, String enquiry) {
        this.author = author;
        this.enquiry = enquiry;
        this.ID = uid;
        this.status = QueryStatus.PENDING; // PENDING is the same as unanswered, ACCEPTED is the same as answered
        this.campID = campID;
    }


    /**
     * Get the status of the query.
     *
     * @return The status of the query.
     * {@link QueryStatus}
     */
    @Override
    public QueryStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status of the query.
     *
     * @param status The new status to set.
     */
    @Override
    public void setStatus(QueryStatus status) {
        this.status = status;
    }

    /**
     * Get the author of the query.
     *
     * @return The author of the query.
     */
    @Override
    public String getAuthor() {
        return this.author;
    }

    /**
     * Get the unique identifier of the query.
     *
     * @return The unique identifier of the query.
     */
    @Override
    public String getID() {
        return this.ID.toString();
    }


    /**
     * Get the camp ID associated with the query.
     *
     * @return The camp identifier associated with the query.
     */
    @Override
    public String getCampID() {
        return campID;
    }

    /**
     * Set the camp ID associated with the query.
     *
     * @param campID The new camp identifier to set.
     */
    @Override
    public void setCampID(String campID) {
        this.campID = campID;
    }

    /**
     * Get the text of the query.
     *
     * @return The text of the query.
     */
    @Override
    public String getQuery() {
        return this.enquiry;
    }


    /**
     * Set the text of the query.
     *
     * @param query The new text of the query.
     */
    @Override
    public void setQuery(String query) {
        this.enquiry = query;
    }

    /**
     * Set the response to the query.
     *
     * @param response The response to set.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Get the response to the query.
     *
     * @return The response to query.
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Set the respondent for the query.
     *
     * @param respondent The respondent to set.
     */ 
    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }

    /**
     * Get the respondent for the query.
     *
     * @return The respondent for the query.
     */
    public String getRespondent() {
        return this.respondent;
    }
}
