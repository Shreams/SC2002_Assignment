package org.example.query.interfaces;

import java.util.ArrayList;
/**
 * This interface defines methods for retrieving queries based on index, user ID, and camp ID.
 *
 * @author Group1
 * @version 1.0
 */
public interface IQueryControllerGetters {
    /**
     * Retrieves a specific query by its index and user ID.
     *
     * @param idx The index of the query to retrieve.
     * @param userID The ID of the user for whom the query is retrieved.
     * @return The query as a String.
     */
    public String getMyQueryFromIdx (int idx, String userID);
    
    /**
     * Retrieves a specific camp query by its index and camp ID.
     *
     * @param idx The index of the camp query to retrieve.
     * @param campID The ID of the camp for which the query is retrieved.
     * @return The camp query as a String.
     */
    public String getCampQueryFromIdx ( int idx, String campID);
    
    /**
     * Retrieves all queries associated with a specific user.
     *
     * @param userID The ID of the user for whom all queries are retrieved.
     * @return A list of queries as Strings.
     */
    public ArrayList<String> getAllQueryByUserID(String userID);
    
    /**
     * Retrieves all queries associated with a specific camp.
     *
     * @param campID The ID of the camp for which all queries for this camp are retrieved.
     * @return A list of camp queries as Strings.
     */
    public ArrayList<String> getAllQueryByCampID(String campID);

}
