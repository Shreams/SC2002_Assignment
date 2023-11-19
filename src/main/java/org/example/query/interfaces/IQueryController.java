package org.example.query.interfaces;

import java.util.ArrayList;

public interface IQueryController {

    public void createQuery(String userID, String campID);

    public ArrayList<String> getAllQueryByUserID(String userID);
    public ArrayList<String> getAllQueryByCampID(String campID);

    public void editQuery(String userID, String queryID);
    public void deleteQuery(String userID, String queryID);

    public void showView(IQueryView view);

    public boolean isQueryExist (String queryID);

    public String getCampQueryFromIdx ( int idx, String campID);
    public boolean replyToQuery(String queryID, String respondentID);
    public boolean hasAnyQuery(String userID);
    public String getMyQueryFromIdx (int idx, String userID);
    public void showQueryDetails(String queryID);
    public boolean hasAnyQueryOfThatCamp(String campID);
    public void viewAllQueryOfThatCamp(String campID);
    public void viewMyQuery(String userID);


}
