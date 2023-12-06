package org.example.query.interfaces;

/**
 * This interface defines methods for creating, deleting, editing, and replying to queries.
 *
 *
 * @author Group1
 * @version 1.0
 */
public interface IQueryControllerCRUD {

    /**
     * Creates a new query associated with a user and a campaign.
     *
     * @param userID The ID of the user creating the query.
     * @param campID The ID of the campaign for which the query is created.
     */
    public void createQuery(String userID, String campID);
    
    /**
     * Deletes a query identified by its ID.
     *
     * @param userID   The ID of the user attempting to delete the query.
     * @param queryID  The ID of the query to be deleted.
     */
    public void deleteQuery(String userID, String queryID);
    
    /**
     * Edits an existing query identified by its ID.
     *
     * @param userID   The ID of the user attempting to edit the query.
     * @param queryID  The ID of the query to be edited.
     */
    public void editQuery(String userID, String queryID);
    
    /**
     * Replies to an existing query identified by its ID.
     *
     * @param queryID       The ID of the query to which the reply is made.
     * @param respondentID  The ID of the user replying to the query.
     * @return true if the reply was successful, false otherwise.
     */
    public boolean replyToQuery(String queryID, String respondentID);
}
