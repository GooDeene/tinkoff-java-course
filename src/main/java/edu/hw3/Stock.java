package edu.hw3;

public record Stock(String name, Integer priceInCents) {

    @Override public String toString() {
        return "Name: %s, price: %s cents".formatted(this.name, this.priceInCents);
    }
}
