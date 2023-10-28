package edu.hw3;

import java.util.Comparator;

public class SecondWordComparator implements Comparator<String> {
    @Override public int compare(String s1, String s2) {
        String[] parts1 = s1.split(" ");
        String[] parts2 = s2.split(" ");

        String partToCompare1 = parts1.length > 1 ? parts1[1] : parts1[0];
        String partToCompare2 = parts2.length > 1 ? parts2[1] : parts2[0];

        return partToCompare1.compareTo(partToCompare2);
    }
}
