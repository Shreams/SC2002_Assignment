package org.example.filters.controllers;

import org.example.camps.interfaces.ICampView;
import org.example.camps.models.CampModel;
import org.example.camps.views.PrintCampDetailsSummarize;
import org.example.filters.utilities.CustomComparator;
import org.example.filters.views.FilterByMenu;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;


/**
 * The FilterViewController class handles filtering and displaying camps based on various criteria.
 *
 * @author Group1
 * @version 1.0
 */
public class FilterViewController {
    //

    private HashMap<String, CampModel> camps;
    private HashMap<String, CampModel> tempCamp;
    private TreeMap<String, CampModel> sortedByCampNames;

    private ArrayList<String> tempCampName;
    private ICampView view;


    /**
     * Default constructor for FilterViewController.
     */
    public FilterViewController(){};



    /**
     * Constructor for FilterViewController that takes in a HashMap of camps.
     *
     * @param camps The HashMap containing camps.
     */
    public FilterViewController(HashMap<String, CampModel> camps) {
        this.camps = camps;

    }


    /**
     * View camps filtered by start date based on various criteria.
     *
     * @param userID           The user ID.
     * @param requireVisibility Boolean indicating whether visibility is required.
     * @param facultyName      The faculty name.
     * @param parsedDate       The parsed date.
     * @param viewAll          Boolean indicating whether to view all camps.
     * @return An ArrayList of camp names filtered by start date.
     */
    public ArrayList<String> viewCampsFilterByStartDate(String userID, boolean requireVisibility, String facultyName,
                                                        Date parsedDate, boolean viewAll) {
        int idx = 1;
        // for GMT+8
        ZoneOffset gmtPlus8 = ZoneOffset.ofHours(8);
        int yearInput = parsedDate.toInstant().atZone(gmtPlus8).getYear();
        int monthInput = parsedDate.toInstant().atZone(gmtPlus8).getMonthValue();
        int dayInput = parsedDate.toInstant().atZone(gmtPlus8).getDayOfMonth();
        tempCamp = new HashMap<>();

        tempCampName = new ArrayList<>();

        if (!requireVisibility) {
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();

//			System.out.println(camp.getCampName());
                // end
                Date StartDate = camp.getDates()[0];

                ZonedDateTime zonedDateTime = StartDate.toInstant().atZone(gmtPlus8);

                // Compare the dates
                // if same is 0
                if (zonedDateTime.toLocalDate().compareTo(LocalDate.of(yearInput, monthInput, dayInput)) != 0) {
                    continue;
                }

                if (viewAll) {
                    tempCamp.put(entry.getKey(), camp);
                } else if ((camp.getStaffInChargeID().equals(userID)) && !viewAll) {
                    tempCamp.put(entry.getKey(), camp);
                }

            }
        } else {
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();

//			System.out.println(camp.getCampName());
                // start date
                Date StartDate = camp.getDates()[0];

                ZonedDateTime zonedDateTime = StartDate.toInstant().atZone(gmtPlus8);

                // Compare the dates
                // if same is 0
                if (zonedDateTime.toLocalDate().compareTo(LocalDate.of(yearInput, monthInput, dayInput)) != 0) {
                    continue;
                }
                if (viewAll && (camp.isVisibility() && (camp.getCampAccessbility().equals(facultyName)
                        || camp.getCampAccessbility().equals("NTU")))) {
                    tempCamp.put(entry.getKey(), camp);
                } else {
                    if (zonedDateTime.toLocalDate().compareTo(LocalDate.of(yearInput, monthInput, dayInput)) != 0) {
                        continue;
                    }
                    boolean isCC = false;
                    for (String str : camp.getCampCommittee()) {
                        if (str.equals(userID)) {
                            tempCamp.put(entry.getKey(), camp);
                            isCC = true;
                            break;
                        }

                    }
                    if (!isCC) {
                        for (String str : camp.getParticipants()) {
                            if (str.equals(userID)) {
                                tempCamp.put(entry.getKey(), camp);
                                break;
                            }

                        }
                    }

//						tempCamp.put(entry.getKey(), camp);

                }

            }

        }
        sortedByCampNames = new TreeMap<>(new CustomComparator());
        sortedByCampNames.putAll(tempCamp);
        if (sortedByCampNames.isEmpty()) {
            System.out.println("No such Start date");
            return null;
        }
        for (CampModel camp : sortedByCampNames.values()) {

            System.out.print(idx + ".");
            idx++;
            Date[] dates = camp.getDates();
            int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
            int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();

            this.view = new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1],
                    camp.getRegistrationEndDate(), remainingSlots, remainingCommitteeSlots);
            this.view.display();
        }
        for (CampModel camp : sortedByCampNames.values()) {
            tempCampName.add(camp.getCampName());
        }

        return tempCampName;
    }

    /**
     * Retrieves camps that a user is associated with, either as a staff member or a participant.
     *
     * @param userID      The user ID for retrieving associated camps.
     * @param isStudent   A boolean indicating whether the user is a student.
     * @param facultyName The faculty name for filtering camps.
     * @return An ArrayList containing names of camps associated with the user.
     *         Returns null if the user has no associated camps.
     */
    public ArrayList<String> viewMyCamps(String userID, boolean isStudent, String facultyName) {
        int idx = 1;
        System.out.println("#################################");
        System.out.println("##     My Camps                ##");
        System.out.println("#################################");
        System.out.println();
        tempCamp = new HashMap<>();
        tempCampName = new ArrayList<>();

        if (!isStudent) {
            // For staff to see all the camps they created
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();
                if (camp.getStaffInChargeID().equals(userID)) {
                    tempCamp.put(entry.getKey(), camp);
                }
            }
        } else {

            // For student to see all the camps they joined
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();
                if (camp.getParticipants().contains(userID) || camp.getCampCommittee().contains(userID)) {
                    tempCamp.put(entry.getKey(), camp);

                }
            }
        }

        sortedByCampNames = new TreeMap<>(new CustomComparator());
        sortedByCampNames.putAll(tempCamp);

        if (sortedByCampNames.isEmpty()) {
            System.out.println("you have no camps");
            return null;
        }

        for (CampModel camp : sortedByCampNames.values()) {

            System.out.print(idx + ".");
            idx++;
            Date[] dates = camp.getDates();
            int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
            int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();

            this.view = new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1],
                    camp.getRegistrationEndDate(), remainingSlots, remainingCommitteeSlots);
            this.view.display();
        }
        for (CampModel camp : sortedByCampNames.values()) {
            tempCampName.add(camp.getCampName());
        }

        return tempCampName;
    }

    /**
     * Retrieves camps filtered by registration date, based on specific criteria.
     *
     * @param userID            The user ID for filtering camps.
     * @param requireVisibility A boolean indicating whether visibility is required.
     * @param facultyName       The faculty name for filtering camps.
     * @param parsedDate        The parsed registration end date for filtering camps.
     * @param viewAll           A boolean indicating whether to view all camps.
     * @return An ArrayList containing names of camps filtered by registration date.
     *         Returns null if no camps match the criteria.
     */
    public ArrayList<String> viewCampsFilterByRegDate(String userID, boolean requireVisibility, String facultyName,
                                                      Date parsedDate, boolean viewAll) {
        // start is 1
        int idx = 1;
        // for GMT+8
        ZoneOffset gmtPlus8 = ZoneOffset.ofHours(8);
        int yearInput = parsedDate.toInstant().atZone(gmtPlus8).getYear();
        int monthInput = parsedDate.toInstant().atZone(gmtPlus8).getMonthValue();
        int dayInput = parsedDate.toInstant().atZone(gmtPlus8).getDayOfMonth();
        tempCamp = new HashMap<>();
        tempCampName = new ArrayList<>();

        if (!requireVisibility) {
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();

//			System.out.println(camp.getCampName());
                // end
                Date StartDate = camp.getRegistrationEndDate();

                ZonedDateTime zonedDateTime = StartDate.toInstant().atZone(gmtPlus8);

                // Compare the dates
                // if same is 0
                if (zonedDateTime.toLocalDate().compareTo(LocalDate.of(yearInput, monthInput, dayInput)) != 0) {
                    continue;

                }

                if (viewAll) {
                    tempCamp.put(entry.getKey(), camp);
                } else if ((camp.getStaffInChargeID().equals(userID)) && viewAll == false) {
                    tempCamp.put(entry.getKey(), camp);
                }

            }

        } else {
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();

//			System.out.println(camp.getCampName());
                // end
                Date StartDate = camp.getRegistrationEndDate();

                ZonedDateTime zonedDateTime = StartDate.toInstant().atZone(gmtPlus8);

                // Compare the dates
                // if same is 0
                if (zonedDateTime.toLocalDate().compareTo(LocalDate.of(yearInput, monthInput, dayInput)) != 0) {
                    continue;
                }
                if (viewAll && camp.isVisibility() && (camp.getCampAccessbility().equals(facultyName)
                        || camp.getCampAccessbility().equals("NTU"))) {
                    tempCamp.put(entry.getKey(), camp);
                } else {
                    if (zonedDateTime.toLocalDate().compareTo(LocalDate.of(yearInput, monthInput, dayInput)) != 0) {
                        continue;
                    }
                    boolean isCC = false;
                    for (String str : camp.getCampCommittee()) {
                        if (str.equals(userID)) {
                            tempCamp.put(entry.getKey(), camp);
                            isCC = true;
                            break;
                        }

                    }
                    if (!isCC) {
                        for (String str : camp.getParticipants()) {
                            if (str.equals(userID)) {
                                tempCamp.put(entry.getKey(), camp);
                                break;
                            }

                        }
                    }

//						tempCamp.put(entry.getKey(), camp);

                }

            }

        }
        sortedByCampNames = new TreeMap<>(new CustomComparator());
        sortedByCampNames.putAll(tempCamp);
        if (sortedByCampNames.isEmpty()) {
            System.out.println("No such End date");
            return null;
        }
        for (CampModel camp : sortedByCampNames.values()) {

            System.out.print(idx + ".");
            idx++;
            Date[] dates = camp.getDates();
            int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
            int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();

            this.view = new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1],
                    camp.getRegistrationEndDate(), remainingSlots, remainingCommitteeSlots);
            this.view.display();
        }
        for (CampModel camp : sortedByCampNames.values()) {
            tempCampName.add(camp.getCampName());
        }

        return tempCampName;
    }

    /**
     * Retrieves camps filtered by location, based on specific criteria.
     *
     * @param userID            The user ID for filtering camps.
     * @param requireVisibility A boolean indicating whether visibility is required.
     * @param facultyName       The faculty name for filtering camps.
     * @param findLocation      The location to search for in camps.
     * @param viewAll           A boolean indicating whether to view all camps.
     * @return An ArrayList containing names of camps filtered by location.
     *         Returns null if no camps match the criteria.
     */
    public ArrayList<String> viewCampsFilterByLocation(String userID, boolean requireVisibility, String facultyName, String findLocation, boolean viewAll) {
        int idx = 1;
        tempCamp = new HashMap<>();
        tempCampName = new ArrayList<>();

//		System.out.println("The list is sorted in alphabetical order of camp names with the filter location");
//		System.out.println("The same char, the shorter string first");
//		System.out.println("UpperCase first then smaller case");
//		System.out.println();

        if (!requireVisibility) {
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();
                if (!camp.getLocation().equalsIgnoreCase(findLocation)) {
                    continue;
                }
                if (viewAll) {
                    tempCamp.put(entry.getKey(), camp);
                } else if ((camp.getStaffInChargeID().equals(userID)) && viewAll == false) {
                    tempCamp.put(entry.getKey(), camp);
                }
            }

        } else {
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();
                //
                if (viewAll && camp.isVisibility()
                        && (camp.getCampAccessbility().equals(facultyName) || camp.getCampAccessbility().equals("NTU"))
                        && camp.getLocation().equalsIgnoreCase(findLocation)) {

                    tempCamp.put(entry.getKey(), camp);

                } else {
                    if (!camp.getLocation().equalsIgnoreCase(findLocation)) {
                        continue;
                    }
                    {
                        boolean isCC = false;
                        for (String str : camp.getCampCommittee()) {
                            if (str.equals(userID)) {
                                tempCamp.put(entry.getKey(), camp);
                                isCC = true;
                                break;
                            }

                        }
                        if (!isCC) {
                            for (String str : camp.getParticipants()) {
                                if (str.equals(userID)) {
                                    tempCamp.put(entry.getKey(), camp);
                                    break;
                                }

                            }
                        }

//							tempCamp.put(entry.getKey(), camp);

                    }
                }

            }
        }

        sortedByCampNames = new TreeMap<>(new CustomComparator());
        sortedByCampNames.putAll(tempCamp);

        if (sortedByCampNames.isEmpty()) {
            System.out.println("No such location");
            return null;
        }
//		System.out.println("tree");
//			System.out.println(tempCamp.size());
//			System.out.println(sortedByCampNames.size());
        for (CampModel camp : sortedByCampNames.values()) {

            System.out.print(idx + ".");
            idx++;
            Date[] dates = camp.getDates();
            int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
            int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();

            this.view = new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1],
                    camp.getRegistrationEndDate(), remainingSlots, remainingCommitteeSlots);
            this.view.display();
        }

        for (CampModel camp : sortedByCampNames.values()) {
            tempCampName.add(camp.getCampName());
        }

        return tempCampName;
    }


    /**
     * Retrieves camps filtered by camp name, based on specific criteria.
     *
     * @param userID            The user ID for filtering camps.
     * @param requireVisibility A boolean indicating whether visibility is required.
     * @param facultyName       The faculty name for filtering camps.
     * @param campName          The camp name to search for in camps.
     * @param viewAll           A boolean indicating whether to view all camps.
     * @return An ArrayList containing names of camps filtered by camp name.
     *         Returns null if no camps match the criteria.
     */
    public ArrayList<String> viewCampsFilterByCampName(String userID, boolean requireVisibility, String facultyName, String campName, boolean viewAll) {
        int idx = 1;
        tempCamp = new HashMap<>();
        tempCampName = new ArrayList<>();

        if (!requireVisibility) {
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();
                if (!camp.getCampName().equalsIgnoreCase(campName)) {
                    continue;
                }
                if (viewAll) {
                    tempCamp.put(entry.getKey(), camp);
                } else if ((camp.getStaffInChargeID().equals(userID)) && viewAll == false) {
                    tempCamp.put(entry.getKey(), camp);
                }
            }

        } else {
            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();
                //
                if (viewAll && camp.isVisibility()
                        && (camp.getCampAccessbility().equals(facultyName) || camp.getCampAccessbility().equals("NTU"))
                        && camp.getCampName().equalsIgnoreCase(campName)) {

                    tempCamp.put(entry.getKey(), camp);

                } else {
                    if (!camp.getCampName().equalsIgnoreCase(campName)) {
                        continue;
                    }
                    {
                        boolean isCC = false;
                        for (String str : camp.getCampCommittee()) {
                            if (str.equals(userID)) {
                                tempCamp.put(entry.getKey(), camp);
                                isCC = true;
                                break;
                            }

                        }
                        if (!isCC) {
                            for (String str : camp.getParticipants()) {
                                if (str.equals(userID)) {
                                    tempCamp.put(entry.getKey(), camp);
                                    break;
                                }

                            }
                        }

//							tempCamp.put(entry.getKey(), camp);

                    }
                }

            }
        }

        sortedByCampNames = new TreeMap<>(new CustomComparator());
        sortedByCampNames.putAll(tempCamp);

        if (sortedByCampNames.isEmpty()) {
            System.out.println("No such Camp Name");
            return null;
        }
//		System.out.println("tree");
//			System.out.println(tempCamp.size());
//			System.out.println(sortedByCampNames.size());
        for (CampModel camp : sortedByCampNames.values()) {

            System.out.print(idx + ".");
            idx++;
            Date[] dates = camp.getDates();
            int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
            int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();

            this.view = new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1],
                    camp.getRegistrationEndDate(), remainingSlots, remainingCommitteeSlots);
            this.view.display();
        }

        for (CampModel camp : sortedByCampNames.values()) {
            tempCampName.add(camp.getCampName());
        }

        return tempCampName;
    }

    /**
     * Displays all camps based on specific criteria and returns an ArrayList of camp names.
     *
     * @param userID            The user ID for filtering camps.
     * @param requireVisibility A boolean indicating whether visibility is required.
     * @param facultyName       The faculty name for filtering camps.
     * @return An ArrayList containing names of camps that meet the criteria.
     *         Returns null if no camps are created yet or if none meet the filtering criteria.
     */
    public ArrayList<String> viewAllCamps(String userID, boolean requireVisibility, String facultyName) {
        int idx = 1;
        System.out.println("#################################");
        System.out.println("##     All Camps               ##");
        System.out.println("#################################");
        System.out.println();

        boolean isAnyCampVisible = false;
        tempCamp = new HashMap<>();
        tempCampName = new ArrayList<>();

        if (camps.isEmpty()) {
            System.out.println("No camps created yet");
            return null;
        }

        if (!requireVisibility) {
            // For staff

            tempCamp.putAll(camps);

        } else { // For student
            // First check if there is any camp that is visible
            // If no then i will print out "No camps created yet"
            // If yes then i will print out the camps that are visible and under the
            // faculty/NTU

            for (Map.Entry<String, CampModel> entry : camps.entrySet()) {
                CampModel camp = entry.getValue();

                if (camp.isVisibility() && (camp.getCampAccessbility().equals(facultyName)
                        || camp.getCampAccessbility().equals("NTU"))) {

                    tempCamp.put(entry.getKey(), camp);

                }

            }
        }
        // put in a treemap
        // all the alphabet will be together
        sortedByCampNames = new TreeMap<>(new CustomComparator());
        sortedByCampNames.putAll(tempCamp);
        camps.clear();
        camps.putAll(sortedByCampNames);

        for (CampModel camp : sortedByCampNames.values()) {
            isAnyCampVisible = true;
            System.out.print(idx + ".");
            idx++;
            Date[] dates = camp.getDates();
            int remainingSlots = camp.getTotalSlots() - camp.getCampCommitteeSlots() - camp.getParticipants().size();
            int remainingCommitteeSlots = camp.getCampCommitteeSlots() - camp.getCampCommittee().size();

            this.view = new PrintCampDetailsSummarize(camp.getCampName(), camp.getLocation(), dates[0], dates[1],
                    camp.getRegistrationEndDate(), remainingSlots, remainingCommitteeSlots);
            this.view.display();
        }

        for (CampModel camp : sortedByCampNames.values()) {

            tempCampName.add(camp.getCampName());

        }

        return tempCampName;

    }


    /**
     * Method to display the menu and select a choice.
     *
     * @return The selected choice.
     */
    public int viewMenuSelectChoice() {
        Scanner sc = new Scanner(System.in);
        while(true){
            try{
                (new FilterByMenu()).display();

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice < 1 || choice > 6) {
                    throw new InputMismatchException("Invalid choice. ");
                }

                return choice;

            }catch (Exception e) {
                System.out.println(e.getMessage());
                sc.nextLine();

            }
        }
    }

}

