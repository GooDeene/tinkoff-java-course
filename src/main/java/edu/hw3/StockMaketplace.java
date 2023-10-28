package edu.hw3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMaketplace implements StockMarket {
    private final PriorityQueue<Stock> stocksQueue = new PriorityQueue<>(new Comparator<Stock>() {
        @Override
        public int compare(Stock o1, Stock o2) {
            return Integer.compare(o2.getPrice(), o1.getPrice());
        }
    });

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
