package org.example.extractexcel;

import org.example.users.enums.UserType;

import java.util.ArrayList;

public class ExtractExcelController {

    // There will be alot of model so we can use arraylist
//    private ArrayList<ExtractExcelModel> models;

    private ExtractExcelView view;

    public ExtractExcelController(ExtractExcelView view) {
        this.view = view;
//        this.models = new ArrayList<ExtractExcelModel>();
    }

    public ArrayList<ExtractExcelModel> getExcelData(String filePath, UserType userType, int colstart,  int colEnd,int rowEnd) {

        String name, email, facutly, userID;

        ArrayList<ExtractExcelModel> models = new ArrayList<ExtractExcelModel>();

        // Call the ExtractExcelExtraction to extract data and return arrayList of string
        ExtractExcelExtraction extractor = new ExtractExcelExtraction(filePath, userType, colstart, colEnd,rowEnd);

        ArrayList<String> data = extractor.extraction();

        // Loop through the arrayList and extract the data
        for (int i = 0; i < data.size(); i += 4) {

            // Skip the delimiter
            if (data.get(i).equals(","))
                continue;

            name = data.get(i);
            email = data.get(i + 1);
            facutly = data.get(i + 2);

            // UserID is the substring before @ in email
            userID = email.substring(0, email.indexOf("@"));

            models.add(new ExtractExcelModel(name, email, facutly, userType, userID));

        }

        return models;

    }

//    public ArrayList<ExtractExcelModel> getModels() {
//        return models;
//    }
//
//    public void printExcelDetails() {
//        for (ExtractExcelModel model : models) {
//            view.printExcelDetails(model.getName(), model.getEmail(), model.getFacultyName(), model.getUserID());
//        }
//    }


}
