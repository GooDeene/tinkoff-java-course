package edu.hw3;

import java.util.Arrays;
import java.util.Objects;

public class Task5 {
    private Task5() {
    }

    private static final String DESCENDING_ORDER_KEYWORD = "DESC";
    private static final String ASCENDING_ORDER_KEYWORD = "ASC";

    public static Contact[] parseContacts(String[] contacts, String sortingOrder) {
        if (contacts == null
            || contacts.length == 0
            || !Objects.equals(sortingOrder, ASCENDING_ORDER_KEYWORD)
            && !Objects.equals(sortingOrder, DESCENDING_ORDER_KEYWORD)) {
            return new Contact[0];
        }
        String[] contactsInternal = contacts.clone();
        Contact[] result = new Contact[contacts.length];
        boolean isDescendingOrder = sortingOrder.equals(DESCENDING_ORDER_KEYWORD);

        Arrays.sort(contactsInternal, new SecondWordComparator());

        for (int i = 0; i < contactsInternal.length; i++) {
            int index = isDescendingOrder ? contactsInternal.length - 1 - i : i;
            result[i] = new Contact(contactsInternal[index]);
        }

        return result;
    }
}
