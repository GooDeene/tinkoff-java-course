package edu.hw3;

import java.util.PriorityQueue;

public class StockMaketplace implements StockMarket {
    private final PriorityQueue<Stock> stocksQueue =
        new PriorityQueue<>((o1, o2) -> Integer.compare(o2.priceInCents(), o1.priceInCents()));

    @Override public void add(Stock stock) {
        stocksQueue.offer(stock);
    }

    @Override public void remove(Stock stock) {
        stocksQueue.remove(stock);
    }

    @Override public Stock mostValuableStock() {
        return stocksQueue.peek();
    }
}
