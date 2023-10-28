package edu.hw3;

import java.util.Comparator;

public class NullAvailableComparator implements Comparator<String> {
    // В данной реализации null-значение имеет приоритет (null first)
    @Override
    public int compare(String s1, String s2) {
        if (s1 == null) {
            return (s2 == null) ? 0 : -1;
        } else if (s2 == null) {
            return 1;
        } else {
            return s1.compareTo(s2);
        }
    }
}
