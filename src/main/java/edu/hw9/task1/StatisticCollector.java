package edu.hw9.task1;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

public class StatisticCollector {
    private final AtomicInteger counter;
    private final List<Metric> allMetrics;
    private final ExecutorService executorService;

    public StatisticCollector(int threadCount) {
        this.counter = new AtomicInteger(0);
        this.allMetrics = new CopyOnWriteArrayList<>();
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void push(String metricName, double... values) {
        counter.incrementAndGet();
        executorService.execute(() -> {
            allMetrics.add(collectStat(metricName, values));
            counter.decrementAndGet();
        });
    }

    private Metric collectStat(String metricName, double... values) {
        return new Metric(
            metricName,
            DoubleStream.of(values).sum(),
            DoubleStream.of(values).average()
                .orElseThrow(() -> new RuntimeException("Нет значений метрики!")),
            DoubleStream.of(values).min().orElseThrow(),
            DoubleStream.of(values).max().orElseThrow()
        );
    }

    public List<Metric> stats() {
        while (counter.get() != 0) {
        }

        return allMetrics;
    }
}
