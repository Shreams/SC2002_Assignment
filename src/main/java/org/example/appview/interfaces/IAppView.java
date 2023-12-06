package org.example.appview.interfaces;
/**
 * The {@code IAppView} interface represents the contract for classes that provide display functionality
 * for various views in the application.
 *
 * Implementing classes must provide a {@code display} method to show information to the user.
 *
 * @author Group1
 * @version 1.0
 */
public interface IAppView {
	/**
     * Displays information to the user based on the specific implementation.
     */
    public void display();
}
