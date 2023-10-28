package edu.hw3;

public class Contact {
    private final String name;
    private final String surname;

    public Contact(String contactName) {
        if (contactName.contains(" ")) {
            String[] parts = contactName.split(" ");
            this.name = parts[0];
            this.surname = parts[1];
        } else {
            this.surname = "";
            this.name = contactName;
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof Contact compContact)) {
            return false;
        }

        return name.equals(compContact.name)
            && surname.equals(compContact.surname);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override public String toString() {
        return "%s %s".formatted(name, surname);
    }
}
