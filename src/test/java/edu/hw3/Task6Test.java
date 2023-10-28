package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    public void correctWorksOperations() {
        StockMaketplace market = new StockMaketplace();

        Stock s1 = new Stock("stock1", 7);
        Stock s2 = new Stock("stock2", 9);
        Stock s3 = new Stock("stock3", 10);
        Stock s4 = new Stock("stock4", 8);

        market.add(s1);
        market.add(s2);
        market.add(s3);
        market.add(s4);
        market.add(s1);

        Assertions.assertEquals(s3, market.mostValuableStock());

        market.remove(s3);
        Assertions.assertEquals(s2, market.mostValuableStock());

        market.remove(s2);
        Assertions.assertEquals(s4, market.mostValuableStock());
    }

    @Test
    public void operationsCorrectWorksWithEmptyMarket() {
        StockMaketplace market = new StockMaketplace();
        // При попытке удалить что-то из пустой биржи исключение НЕ будет вызвано
        market.remove(new Stock("test", 10));

        Assertions.assertNull(market.mostValuableStock());
    }

    @Test
    public void stockToSctingWorksCorrectly() {
        Stock stock = new Stock("test", 10);
        Assertions.assertEquals("Name: test, id: 0, price: 10", stock.toString());
    }

    @Test
    public void stockGetMethodsWorksCorrectly() {
        Stock stock = new Stock("stockName", 19);
        Assertions.assertEquals("stockName", stock.getName());
        Assertions.assertEquals(19, stock.getPrice());
    }
}
