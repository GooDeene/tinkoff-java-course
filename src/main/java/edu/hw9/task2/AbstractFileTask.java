package edu.hw9.task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public abstract class AbstractFileTask extends RecursiveTask<List<File>> {
    protected final File file;

    protected AbstractFileTask(File file) {
        this.file = file;
    }

    @Override
    protected List<File> compute() {
        if (file == null) {
            return null;
        }
        var filesInDirectory = file.listFiles();
        if (filesInDirectory == null) {
            return List.of();
        }
        return processFiles(filesInDirectory);
    }

    protected abstract List<File> processFiles(File[] filesInDirectory);
}
