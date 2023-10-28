package edu.hw3;

import java.util.HashMap;

public class Task3 {
    private Task3() {
    }

    public static HashMap<Object, Integer> freqDict(Object[] inputData) {
        if (inputData == null) {
            return null;
        }
        HashMap<Object, Integer> freqDictionary = new HashMap<>();
        for (Object element : inputData) {
            freqDictionary.put(element, freqDictionary.getOrDefault(element, 0) + 1);
        }
        return freqDictionary;
    }
}
