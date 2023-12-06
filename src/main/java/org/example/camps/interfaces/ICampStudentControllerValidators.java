package org.example.camps.interfaces;

import java.util.Date;

/**
 * This interface provides validation methods for student-related operations in camp controllers.
 *
 * @author Group1
 * @version 1.0
 */
public interface ICampStudentControllerValidators {

    /**
     * Checks if a student is already part of the camp committee of another camp.
     *
     * @param studentID The ID of the student.
     * @return True if the student is already part of the committee of another camp; otherwise, false.
     */
    boolean isAlreadyPartOfCampCommitteeOfAnotherCamp(String studentID);

    /**
     * Checks if the dates of a camp overlap with dates of another camp the student is enrolled in.
     *
     * @param campName The name of the camp.
     * @param userID   The ID of the student.
     * @return True if the camp dates overlap with another camp's dates; otherwise, false.
     */
    boolean isCampDateOverlapping(String campName, String userID);

    /**
     * Checks if the student is past the registration date for a camp.
     *
     * @param campName    The name of the camp.
     * @param currentDate The current date.
     * @return True if the student is past the registration date for the camp; otherwise, false.
     */
    boolean isStudentPastRegistrationDate(String campName, Date currentDate);

    /**
     * Checks if the student is already part of the camp committee.
     *
     * @param campName  The name of the camp.
     * @param studentID The ID of the student.
     * @return True if the student is already part of the camp committee; otherwise, false.
     */
    boolean isStudentAlreadyPartOfCampCommittee(String campName, String studentID);

    /**
     * Checks if the camp committee is full for a specific camp.
     *
     * @param campName The name of the camp.
     * @return True if the camp committee is full; otherwise, false.
     */
    boolean isCampCommitteeFull(String campName);

    /**
     * Checks if the student is already part of a specific camp.
     *
     * @param campName  The name of the camp.
     * @param studentID The ID of the student.
     * @return True if the student is already part of the camp; otherwise, false.
     */
    boolean isStudentAlreadyPartOfCamp(String campName, String studentID);

    /**
     * Checks if the student is blacklisted from a specific camp.
     *
     * @param campID    The ID of the camp.
     * @param studentID The ID of the student.
     * @return True if the student is blacklisted from the camp; otherwise, false.
     */
    boolean isStudentBlacklisted(String campID, String studentID);
}
