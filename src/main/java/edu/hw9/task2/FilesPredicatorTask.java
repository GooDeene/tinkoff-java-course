package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Predicate;

public class FilesPredicatorTask extends AbstractFileTask {
    private final Predicate<File> predicate;

    public FilesPredicatorTask(File file, Predicate<File> predicate) {
        super(file);
        this.predicate = predicate;
    }

    @Override
    protected List<File> processFiles(File[] filesInDirectory) {
        var tasks = new ArrayList<ForkJoinTask<List<File>>>();
        var result = new ArrayList<File>();

        for (var file : filesInDirectory) {
            if (file.isDirectory()) {
                var task = new FilesPredicatorTask(file, predicate);
                tasks.add(task.fork());
            } else if (predicate.test(file)) {
                result.add(file);
            }
        }

        tasks
            .stream()
            .flatMap(x -> x.join().stream())
            .forEach(result::add);

        return result;
    }

}
