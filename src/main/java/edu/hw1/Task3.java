package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

class Task3 {
    private Task3() {
    }

    public static boolean isNestable(Integer[] firstArray, Integer[] secondArray) {
        if (firstArray.length < 1 || secondArray.length < 1) {
            return false;
        }
        var list1 = Arrays.asList(firstArray);
        var list2 = Arrays.asList(secondArray);
        return Collections.min(list1) > Collections.min(list2) && Collections.max(list1) < Collections.max(list2);
    }
}
