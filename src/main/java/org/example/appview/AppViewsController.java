package org.example.appview;

import org.example.appview.interfaces.IAppView;
import org.example.appview.views.AccountView;
import org.example.appview.views.HomeView;
import org.example.users.enums.UserType;


// A controller that only deals with views
public class AppViewsController {


    private IAppView view;



    public void showHomePage(){
        view = new HomeView();
        view.display();
    }

    public void showAccountViewPage(){
        view = new AccountView();
        view.display();
    }

    public void appRender(IAppView view){
        view.display();
    }
}
