package org.example.query.suggestions;

import org.example.query.enums.QueryStatus;
import org.example.query.interfaces.IQueryModel;

public class SuggestionModel implements IQueryModel {

    private String author;
    private String ID;
    private String suggestion;
    private QueryStatus status;

    private String campID;

    private String respondent;

    public SuggestionModel(String uid, String author, String campID, String suggestion){
        this.author = author;
        this.suggestion = suggestion;
        this.ID = uid;
        this.status = QueryStatus.PENDING;
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
        return this.ID;
    }

    @Override
    public String getQuery() {
        return this.suggestion;
    }

    @Override
    public void setQuery(String query) {
        this.suggestion = query;
    }

    @Override
    public String getCampID() {
        return campID;
    }

    @Override
    public void setCampID(String campID) {
        this.campID = campID;
    }

    public String getRespondent() {
        return respondent;
    }

    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }
}
