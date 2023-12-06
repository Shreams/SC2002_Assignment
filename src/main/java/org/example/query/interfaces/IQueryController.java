package org.example.query.interfaces;

import java.util.ArrayList;

/**
 * The {@code IQueryController} interface represents a controller in a query-based application that
 * combines functionality from multiple interfaces to manage queries and interact with query views.
 * It extends {@link IQueryControllerCRUD}, {@link IQueryControllerGetters},
 * {@link IQueryControllerValidators}, and {@link IQueryControllerView} to provide a comprehensive
 * set of features.
 * 
 * This interface includes a method {@link #showView(IQueryView)} to display a query view.
 *
 * @author Group1
 * @version 1.0
 * @see IQueryControllerCRUD
 * @see IQueryControllerGetters
 * @see IQueryControllerValidators
 * @see IQueryControllerView
 * 
 */
public interface IQueryController extends  IQueryControllerCRUD, IQueryControllerGetters, IQueryControllerValidators, IQueryControllerView {
    /**
     * Displays the specified query view.
     *
     * @param view The query view to be displayed.
     */
	public void showView(IQueryView view);
}
