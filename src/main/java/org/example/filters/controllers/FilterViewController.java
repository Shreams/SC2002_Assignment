package org.example.filters.controllers;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

import org.example.camps.interfaces.ICampView;
//import org.example.camps.controllers.CampController.CustomComparator;
import org.example.camps.models.CampModel;
import org.example.camps.views.PrintCampDetailsSummarize;
import org.example.filters.interfaces.IFilterView;
import org.example.filters.views.FilterByMenu;

public class FilterViewController {
    //

    private HashMap<String, CampModel> camps;
    private HashMap<String, CampModel> tempCamp;
    private TreeMap<String, CampModel> sortedByCampNames;

    private ArrayList<String> tempCampName;
    private ICampView view;

    private IFilterView filterView;

    public FilterViewController(){};

    public FilterViewController(HashMap<String, CampModel> camps) {
        this.camps = camps;

    }

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
                } else if ((camp.getStaffInChargeID().equals(userID)) && viewAll == false) {
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
                if (viewAll && (camp.isVisbility() && (camp.getCampAccessbility().equals(facultyName)
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
                if (viewAll && camp.isVisbility() && (camp.getCampAccessbility().equals(facultyName)
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
                if (viewAll && camp.isVisbility()
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

    public ArrayList<String> viewCampsFilterByCampName(String userID, boolean requireVisibility, String facultyName, String campName, boolean viewAll) {
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
                if (viewAll && camp.isVisbility()
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

                if (camp.isVisbility() && (camp.getCampAccessbility().equals(facultyName)
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


    public void render(IFilterView filterView) {
        this.filterView = filterView;
        this.filterView.display();
    }


    public int menuSelectChoice() {
        Scanner sc = new Scanner(System.in);
        while(true){
            try{
                render(new FilterByMenu());

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

    static class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            // case-insensitive
            // if is abc vs abcd, abc is consider first
            int result = str1.compareToIgnoreCase(str2);
            int keepComparing = 0;

            // If the strings are the same (ignoring case), compare by case-sensitive
            // natural order
            if (result == 0) {
                // keepComparing < Math.min(str1.length(), str2.length()) so dont go out of
                // range
                while (keepComparing >= 0 && result == 0 && keepComparing < Math.min(str1.length(), str2.length())) {
                    result = Character.compare(str1.charAt(keepComparing), str2.charAt(keepComparing));
                    keepComparing++;

                }
            }

            return result;
        }
    }
}

