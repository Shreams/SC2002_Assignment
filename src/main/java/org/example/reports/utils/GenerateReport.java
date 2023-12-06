package org.example.reports.utils;

import org.example.reports.models.ReportModel;

import java.util.HashMap;
import java.util.TreeMap;



/**
 * @author Group1
 * @version 1.0
 *
 * The GenerateReport class is an abstract class that provides methods to generate reports.
 */
public abstract class GenerateReport {

    /**
     * Generates a report based on the provided report model.
     *
     * @param reportModel A TreeMap containing report data where each entry represents a report entry
     *                   with a unique identifier (String) and associated ReportModel.
     */
    public abstract void generateReport(TreeMap<String, ReportModel> reportModel);


    /**
     * Generates a special report for a camp committee, taking into account additional points data.
     *
     * @param reportModel A TreeMap containing report data where each entry represents a report entry
     *                   with a unique identifier (String) and associated ReportModel.
     * @param points      A HashMap containing additional points data for the camp committee report,
     *                   where each entry represents a unique identifier (String) and associated integer points.
     */
    public abstract void generateCampCommitteeReport(TreeMap<String, ReportModel> reportModel, HashMap<String, Integer> points);
}
