package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> inputData) {
        if (inputData == null) {
            return null;
        }
        Map<T, Integer> freqDictionary = new HashMap<>();
        for (T element : inputData) {
            freqDictionary.put(element, freqDictionary.getOrDefault(element, 0) + 1);
        }
        return freqDictionary;
    }
}
