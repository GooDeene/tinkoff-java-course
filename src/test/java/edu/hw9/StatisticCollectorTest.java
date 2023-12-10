package edu.hw9;

import edu.hw9.task1.Metric;
import edu.hw9.task1.StatisticCollector;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatisticCollectorTest {

    @Test
    public void statsCollectorWorksCorrectlyWithCorrectData() throws InterruptedException {
        var collector = new StatisticCollector(5);
        var executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> collector.push("metric1", 1));
        executorService.execute(() -> collector.push("metric2", 1, 4, 3, 4, 5));
        executorService.execute(() -> collector.push("metric3", 1, 9, 3, 4, 5));

        Thread.sleep(1);
        var stats = collector.stats();
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        Assertions.assertThat(stats).containsExactlyInAnyOrder(
            new Metric("metric1", 1, 1, 1, 1),
            new Metric("metric2", 17, 3.4, 1, 5),
            new Metric("metric3", 22, 4.4, 1, 9)
        );
    }

    @Test
    public void statsCollectorWorksCorrectlyWithEmptyData() {
        var collector = new StatisticCollector(3);
        var executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> collector.push("metric1"));

        var stats = collector.stats();
        Assertions.assertThat(stats).isEmpty();
    }
}
