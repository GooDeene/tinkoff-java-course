package edu.hw9;

import edu.hw9.task1.Metric;
import edu.hw9.task1.StatisticCollector;
import java.util.concurrent.Executors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatisticCollectorTest {

    @Test
    public void statsCollectorWorksCorrectlyWithCorrectData() {
        var collector = new StatisticCollector(2);
        var executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> collector.push("metric1", new double[] {1}));
        executorService.execute(() -> collector.push("metric2", new double[] {1, 4, 3, 4, 5}));
        executorService.execute(() -> collector.push("metric3", new double[] {1, 9, 3, 4, 5}));
        executorService.execute(() -> collector.push("metric4", new double[] {0, 44, 3, 4, 5}));

        var stats = collector.stats();
        Assertions.assertThat(stats).containsExactlyInAnyOrder(
            new Metric("metric1", 1, 1, 1, 1),
            new Metric("metric2", 17, 3.4, 5, 1),
            new Metric("metric3", 22, 4.4, 9, 1),
            new Metric("metric4", 56, 11.2, 44, 0)
        );
    }

    @Test
    public void statsCollectorWorksCorrectlyWithEmptyData() {
        var collector = new StatisticCollector(2);
        var executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> collector.push("metric1", new double[0]));

        var stats = collector.stats();
        Assertions.assertThat(stats).isEmpty();
    }
}
