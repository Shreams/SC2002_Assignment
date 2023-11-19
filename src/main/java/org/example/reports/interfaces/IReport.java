package org.example.reports.interfaces;

import org.example.reports.models.ReportModel;

import java.util.HashMap;
import java.util.TreeMap;

public interface IReport {

    public void generateReport(TreeMap<String, ReportModel> reportModel);

    void generateCampCommitteeReport(TreeMap<String, ReportModel> reportModel, HashMap<String, Integer> points);

}
