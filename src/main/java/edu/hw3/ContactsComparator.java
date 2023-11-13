package edu.hw3;

import java.util.Comparator;

public class ContactsComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact o1, Contact o2) {
        String partToCompare1 = o1.name().length() > 1 && o1.surname().length() > 1 ? o1.surname() : o1.name();
        String partToCompare2 = o2.name().length() > 1 && o2.surname().length() > 1 ? o2.surname() : o2.name();

        return partToCompare1.compareTo(partToCompare2);
    }
}
