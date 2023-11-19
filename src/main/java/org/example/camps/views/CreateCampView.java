package org.example.camps.views;

import org.example.camps.interfaces.ICampView;

public class CreateCampView implements ICampView {

    @Override
    public void display() {
        System.out.println("#################################");
        System.out.println("##     Camp Creation           ##");
        System.out.println("#################################");
        System.out.println("Enter the following details: ");
    }
}
