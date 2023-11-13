package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String inputBrackets) {
        if (inputBrackets == null || inputBrackets.isEmpty()) {
            return new ArrayList<>();
        }

        char[] inputAsArray = inputBrackets.toCharArray();
        List<String> result = new ArrayList<>();
        Stack<Character> bracketsStack = new Stack<>();
        int clusterIndex = -1;
        for (int i = 0; i < inputBrackets.length(); i++) {
            var symbol = inputAsArray[i];
            if (symbol == '(') {
                bracketsStack.push(symbol);
            } else {
                // Если строку нельзя разбить на сбалансированные кластеры будет вызвана ошибка
                if (bracketsStack.isEmpty()) {
                    throw new IllegalArgumentException(
                        "Некорректная строка. Невозможно разбить на сбалансированные кластеры!");
                }
                bracketsStack.pop();
            }

            if (bracketsStack.isEmpty()) {
                result.add(inputBrackets.substring(clusterIndex + 1, i + 1));
                clusterIndex = i;
            }
        }
        return result;
    }
}
