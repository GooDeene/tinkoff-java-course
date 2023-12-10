package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatisticCollector {
    private final ExecutorService executorService;
    private final Queue<Metric> allMetrics = new ConcurrentLinkedQueue<>();

    public StatisticCollector(int threadsCount) {
        executorService = Executors.newFixedThreadPool(threadsCount);
    }

    public void push(String metricName, double[] values) {
        executorService.execute(put(metricName, values));
    }

    public List<Metric> stats() {
        return new ArrayList<>(allMetrics);
    }

    private Runnable put(String name, double[] values) {
        return () -> {
            var sum = 0d;
            var max = Double.NEGATIVE_INFINITY;
            var min = Double.POSITIVE_INFINITY;

            for (var value : values) {
                if (value > max) {
                    max = value;
                }

                if (value < min) {
                    min = value;
                }

                sum += value;
            }

            allMetrics.add(
                new Metric(name, sum, sum / values.length, max, min));
        };
    }
}
