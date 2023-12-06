package org.example.query.interfaces;

/**
 * This interface defines methods for validating queries in a query controller.
 *
 * @author Group1
 * @version 1.0
 */
public interface IQueryControllerValidators {
	
    /**
     * Checks if there are any queries associated with a specific user.
     *
     * @param campID The identifier of the user to check for queries.
     * @return true if there are queries for the specified user, false otherwise.
     */
    public boolean hasAnyQueryOfThatCamp(String campID);
    
    /**
     * Checks if any query exist for the given queryID.
     *
     * @param userID The identifier of the query to check for existence.
     * @return true if the query exists, false otherwise.
     */
    public boolean hasAnyQuery(String userID);
    
    /**
     * Checks if a query for the given queryID exists.
     *
     * @param queryID The identifier of the query to check for existence.
     * @return true if the query exists, false otherwise.
     */
    public boolean isQueryExist (String queryID);
}
