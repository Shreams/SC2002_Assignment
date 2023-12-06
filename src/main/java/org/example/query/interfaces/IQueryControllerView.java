package org.example.query.interfaces;
/**
 * The {@code IQueryControllerView} interface defines a set of methods
 * that a query controller view should implement to interact with queries in a system.
 *
 * @author Group1
 * @version 1.0
 */
public interface IQueryControllerView {
    /**
     * Displays all queries associated with a specific camp.
     *
     * @param campID The unique identifier of the camp for which queries should be displayed.
     */
    public void viewAllQueryOfThatCamp(String campID);
    
    /**
     * Displays queries specific to a user.
     *
     * @param userID The unique identifier of the user for whom queries should be displayed.
     */
    public void viewMyQuery(String userID);

    /**
     * Displays the details of a specific query.
     *
     * @param queryID The unique identifier of the query for which details should be displayed.
     */
    public void showQueryDetails(String queryID);



}
