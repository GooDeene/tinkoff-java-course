package edu.hw3;

public record Contact(String name, String surname) {
    public static Contact withFullName(String contactName) {
        if (contactName.contains(" ")) {
            String[] parts = contactName.split(" ");
            return new Contact(parts[0], parts[1]);
        } else {
            return new Contact(contactName, "");
        }
    }
}


