package org.example.extractexcel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.users.enums.UserType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The ExtractExcelExtraction class handles the extraction of data from an Excel file.
 * Requires Apache POI library.
 *
 * @author Group1
 * @version 1.0
 */
public class ExtractExcelExtraction {
    private String filePath;
    private UserType userType;
    private int colStart, colEnd;
    private int rowEnd;
    private ArrayList<String> arrayListString;

    /**
     * Constructs an ExtractExcelExtraction object.
     *
     * @param filePath The path of the Excel file.
     * @param userType The type of user.
     * @param colStart The starting column index for data extraction.
     * @param colEnd   The ending column index for data extraction.
     * @param rowEnd   The ending row index for data extraction.
     */
    public ExtractExcelExtraction(String filePath, UserType userType, int colStart, int colEnd, int rowEnd) {
        this.filePath = filePath;
        this.userType = userType;
        this.colStart = colStart;
        this.colEnd = colEnd;
        this.arrayListString = new ArrayList<String>();
        this.rowEnd = rowEnd;
    }

    /**
     * Extracts data from the Excel file.
     *
     * @return An ArrayList containing extracted data as strings.
     * @throws RuntimeException if any I/O exception occurs during the extraction process.
     */
    public ArrayList<String> extraction() {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int rowNum = 1; rowNum <= (rowEnd - 1); rowNum++) {
                for (int colNum = colStart; colNum < colEnd; colNum++) {
                    arrayListString.add(sheet.getRow(rowNum).getCell(colNum).getStringCellValue());
                }

                // Add a delimiter to separate each row
                arrayListString.add(",");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return arrayListString;
    }
}
