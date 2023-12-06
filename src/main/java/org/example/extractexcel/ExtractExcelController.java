package org.example.extractexcel;

import org.example.users.enums.UserType;
import java.util.ArrayList;

/**
 * The ExtractExcelController class manages the extraction of data from an Excel file.
 *
 * @author Group1
 * @version 1.0
 */
public class ExtractExcelController {

    private ExtractExcelView view;

    /**
     * Constructs an ExtractExcelController object.
     *
     * @param view The view associated with the controller.
     */
    public ExtractExcelController(ExtractExcelView view) {
        this.view = view;
    }

    /**
     * Retrieves Excel data based on specified parameters.
     *
     * @param filePath The path of the Excel file.
     * @param userType The type of user.
     * @param colStart The starting column index for data extraction.
     * @param colEnd   The ending column index for data extraction.
     * @param rowEnd   The ending row index for data extraction.
     * @return An ArrayList containing ExtractExcelModel objects with extracted data.
     */
    public ArrayList<ExtractExcelModel> getExcelData(String filePath, UserType userType, int colStart, int colEnd, int rowEnd) {
        String name, email, faculty, userID;
        ArrayList<ExtractExcelModel> models = new ArrayList<>();

        // Call the ExtractExcelExtraction to extract data and return an ArrayList of strings
        ExtractExcelExtraction extractor = new ExtractExcelExtraction(filePath, userType, colStart, colEnd, rowEnd);
        ArrayList<String> data = extractor.extraction();

        // Loop through the ArrayList and extract the data
        for (int i = 0; i < data.size(); i += 4) {
            // Skip the delimiter
            if (data.get(i).equals(",")) {
                continue;
            }

            name = data.get(i);
            email = data.get(i + 1);
            faculty = data.get(i + 2);

            // UserID is the substring before @ in email
            userID = email.substring(0, email.indexOf("@"));

            models.add(new ExtractExcelModel(name, email, faculty, userType, userID));
        }

        return models;
    }
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



