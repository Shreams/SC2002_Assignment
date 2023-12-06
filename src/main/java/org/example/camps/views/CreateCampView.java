package org.example.camps.views;

import org.example.camps.interfaces.ICampView;

/**
 * Represents the view for creating a camp.
 * Implements the ICampView interface.
 *
 * @author Group1
 * @version 1.0
 */
public class CreateCampView implements ICampView {

    /**
     * Displays the prompt and instructions for camp creation.
     */
    @Override
    public void display() {
        System.out.println("#################################");
        System.out.println("##     Camp Creation           ##");
        System.out.println("#################################");
        System.out.println("Enter the following details: ");
    }
}
