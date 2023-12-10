package edu.hw9.task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public final class FilesTreeProcessor {
    private FilesTreeProcessor() {
    }

    public static List<File> filterFilesRecursively(File file, Predicate<File> predicate) {
        var task = new FilesPredicatorTask(file, predicate);
        var pool = new ForkJoinPool();
        var result = pool.invoke(task);

        pool.shutdown();

        return result;
    }

    public static List<File> searchDirectories(File file, int minFilesCount) {
        var task = new DirectorySearcherTask(file, minFilesCount);
        var pool = new ForkJoinPool();
        var result = pool.invoke(task);

        pool.shutdown();

        return result;
    }
}
