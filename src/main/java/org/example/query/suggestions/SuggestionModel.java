package org.example.query.suggestions;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryModel;

/**
 * @author Group1
 * @version 1.0
 * 
 * A class representing a suggestion model that implements the IQueryModel interface.
 * This class stores information about a suggestion, including its author, ID, content,
 * status, campID, and respondent.
 */
public class SuggestionModel implements IQueryModel {

    private String author;
    private String ID;
    private String suggestion;
    private QueryStatus status;

    private String campID;

    private String respondent;

    /**
     * Constructor to initialize a SuggestionModel object with the provided parameters.
     *
     * @param uid      The unique ID of the suggestion.
     * @param author   The author of the suggestion.
     * @param campID   The campaign ID associated with the suggestion.
     * @param suggestion The content of the suggestion.
     */
    public SuggestionModel(String uid, String author, String campID, String suggestion){
        this.author = author;
        this.suggestion = suggestion;
        this.ID = uid;
        this.status = QueryStatus.PENDING;
        this.campID = campID;
    }

    /**
     * Get the status of the suggestion.
     *
     * @return The status of the suggestion.
     */
    @Override
    public QueryStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status of the suggestion.
     *
     * @param status The new status of the suggestion.
     */
    @Override
    public void setStatus(QueryStatus status) {
        this.status = status;
    }

    /**
     * Get the author of the suggestion.
     *
     * @return The author of the suggestion.
     */
    @Override
    public String getAuthor() {
        return this.author;
    }

    /**
     * Get the unique ID of the suggestion.
     *
     * @return The unique ID of the suggestion.
     */
    @Override
    public String getID() {
        return this.ID;
    }

    /**
     * Get the content of the suggestion.
     *
     * @return The content of the suggestion.
     */
    @Override
    public String getQuery() {
        return this.suggestion;
    }

    /**
     * Set the content of the suggestion.
     *
     * @param query The new content of the suggestion.
     */
    @Override
    public void setQuery(String query) {
        this.suggestion = query;
    }

    /**
     * Get the campaign ID associated with the suggestion.
     *
     * @return The campaign ID associated with the suggestion.
     */
    @Override
    public String getCampID() {
        return campID;
    }

    /**
     * Set the campaign ID associated with the suggestion.
     *
     * @param campID The new campaign ID associated with the suggestion.
     */
    @Override
    public void setCampID(String campID) {
        this.campID = campID;
    }

    /**
     * Get the respondent for the suggestion.
     *
     * @return The respondent for the suggestion.
     */
    public String getRespondent() {
        return respondent;
    }

    /**
     * Set the respondent for the suggestion.
     *
     * @param respondent The new respondent for the suggestion.
     */
    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }
}
