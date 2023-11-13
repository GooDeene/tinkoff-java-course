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
            || !Objects.equals(sortingOrder, ASCENDING_ORDER_KEYWORD)
            && !Objects.equals(sortingOrder, DESCENDING_ORDER_KEYWORD)) {
            throw new IllegalArgumentException(
                "Список контактов не может быть Null и/или ошибка в порядке сортировки!"
            );
        } else if (contacts.length == 0) {
            return new Contact[0];
        }

        Contact[] result = new Contact[contacts.length];
        boolean isDescendingOrder = sortingOrder.equals(DESCENDING_ORDER_KEYWORD);

        for (int i = 0; i < contacts.length; i++) {
            result[i] = Contact.withFullName(contacts[i]);
        }
        Arrays.sort(result, isDescendingOrder ? new ContactsComparator().reversed() : new ContactsComparator());

        return result;
    }
}
