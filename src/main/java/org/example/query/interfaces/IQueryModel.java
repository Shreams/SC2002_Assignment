package org.example.query.interfaces;

import org.example.query.enums.QueryStatus;

/**
 * The {@code IQueryModel} interface represents a query model with basic properties.
 * It defines methods for retrieving and setting the campaign ID, query text, status,
 * author, and ID.
 *
 * @author Group1
 * @version 1.0
 */
public interface IQueryModel {

	/**
     * Gets the campaign ID associated with this query.
     *
     * @return The campaign ID as a string.
     */
    public String getCampID();
    
    /**
     * Sets the campaign ID for this query.
     *
     * @param campID The campaign ID to set.
     */
    public void setCampID(String campID);

    
    /**
     * Gets the query text.
     *
     * @return The query text as a string.
     */
    public String getQuery();
    
    /**
     * Sets the query text.
     *
     * @param query The query text to set.
     */
    public void setQuery(String query);

    
    /**
     * Gets the status of the query.
     *
     * @return The query status.
     */
    public QueryStatus getStatus();
    
    /**
     * Set the status of the query.
     *
     * @param status The query status to be set.
     */
    public void setStatus(QueryStatus status);
    
    /**
     * Gets the author of the query.
     *
     * @return The author's name as a string.
     */
    public String getAuthor();
    
    /**
     * Gets the unique identifier for the query.
     *
     * @return The query's unique identifier as a string.
     */
    public String getID();
}
