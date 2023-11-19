package org.example.filters.views;

import org.example.filters.interfaces.IFilterView;

public class FilterByMenu implements IFilterView {

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
