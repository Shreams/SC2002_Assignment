package org.example.camps.interfaces;

import java.util.ArrayList;

public interface ICampController {


    String getCampName(int idx, String userID);

    void getCampDetails(String campName);

    void viewMyCamps(String userID, String facultyName);

    ArrayList<String> viewAllCamps(String userID, String facultyName); // For staff its all, but for student is subjected to their faculty/ whole NTU

    ArrayList<String> viewCampsByFilter(int filterChoice, String userID, String facultyName, boolean viewAll);

    void render(ICampView view);

    ArrayList<String> getCampNames(String userID, String facultyName);



}
