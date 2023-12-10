package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class DirectorySearcherTask extends AbstractFileTask {
    private final int minimumFilesCountLimit;

    public DirectorySearcherTask(File file, int minimumFilesCountLimit) {
        super(file);
        this.minimumFilesCountLimit = minimumFilesCountLimit;
    }

    @Override
    protected List<File> processFiles(File[] filesInDirectory) {
        var tasks = new ArrayList<ForkJoinTask<List<File>>>();
        var result = new ArrayList<File>();
        var counter = 0;

        for (var file : filesInDirectory) {
            if (!file.isDirectory()) {
                counter++;
                continue;
            }

            var task = new DirectorySearcherTask(file, minimumFilesCountLimit);
            tasks.add(task.fork());
        }

        if (counter >= minimumFilesCountLimit) {
            result.add(file);
        }

        tasks
            .stream()
            .flatMap(x -> x.join().stream())
            .forEach(result::add);

        return result;
    }
}
