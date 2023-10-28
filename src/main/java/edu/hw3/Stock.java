package edu.hw3;

public class Stock {
    private static int currentFreeId = 0;
    private final String name;
    private final Integer price;
    private final Integer id;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
        this.id = currentFreeId;
        currentFreeId++;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof Stock comparableStock)) {
            return false;
        }

        return this.name.equals(comparableStock.name) && this.price.equals(comparableStock.price)
            && this.id.equals(comparableStock.id);
    }

    @Override public int hashCode() {
        return 0;
    }

    @Override public String toString() {
        return "Name: %s, id: %s, price: %s".formatted(this.name, this.id, this.price);
    }
}
