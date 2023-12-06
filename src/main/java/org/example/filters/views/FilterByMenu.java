package org.example.filters.views;

import org.example.filters.interfaces.IFilterView;

/**
 * This class represents a filter menu view for selecting various filter options.
 * It implements the IFilterView interface for displaying filter-related information.
 *
 * @author Group1
 * @version 1.0
 */
public class FilterByMenu implements IFilterView {

    /**
     * Displays the filter menu options to navigate.
     * Options include No Filter, Location, Start Date, Registration End Date, Camp Name, and Back.
     */
    @Override
    public void display() {

        System.out.println("~ Filter by ~");
        System.out.println("Select an option to navigate: ");
        System.out.println("1. No Filter");
        System.out.println("2. Location");
        System.out.println("3. Start Date");
        System.out.println("4. Registration end Date");
        System.out.println("5. Camp Name");
        System.out.println("6. Back");
    }

}
