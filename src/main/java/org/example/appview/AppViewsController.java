package org.example.appview;

import org.example.appview.interfaces.IAppView;
import org.example.appview.views.AccountView;
import org.example.appview.views.HomeView;
import org.example.users.enums.UserType;

/**
 * The {@code AppViewsController} class is a controller responsible for managing display in the application.
 * It deals with displaying different pages, such as the home page and account view page.
 * This class is part of the {@code org.example.appview} package.
 *
 * @author Group1
 * @version 1.0
 */
// A controller that only deals with views
public class AppViewsController {

    /**
     * The display that is chosen to view.
     */
    private IAppView view;


    /**
     * Displays the home page view.
     */
    public void showHomePage(){
        view = new HomeView();
        view.display();
    }

    /**
     * Displays the account view page.
     */
    public void showAccountViewPage(){
        view = new AccountView();
        view.display();
    }

    /**
     * Renders the provided view.
     *
     * @param view The view to render.
     */
    public void appRender(IAppView view){
        view.display();
    }
}
