package org.example.filters.utilities;

import java.util.Comparator;


/**
 * This class implements a custom string comparator that compares strings first in a
 * case-insensitive manner and then by case-sensitive natural order.
 * Implements the Comparator interface for String objects.
 *
 * @version 1.0
 * @author Group1
 */
public class CustomComparator  implements Comparator<String> {

    /**
     * Compares two strings considering case-insensitivity followed by case-sensitive
     * natural order.
     *
     * @param str1 The first string to compare
     * @param str2 The second string to compare
     * @return An integer representing the comparison result:
     *         0 if strings are equal,
     *         less than 0 if str1 is lexicographically less than str2,
     *         greater than 0 if str1 is lexicographically greater than str2
     */
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
