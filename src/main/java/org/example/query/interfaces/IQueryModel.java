package org.example.query.interfaces;

import org.example.query.enums.QueryStatus;

public interface IQueryModel {


    public String getCampID();
    public void setCampID(String campID);

    public String getQuery();
    public void setQuery(String query);

    public QueryStatus getStatus();
    public void setStatus(QueryStatus status);
    public String getAuthor();
    public String getID();
}
