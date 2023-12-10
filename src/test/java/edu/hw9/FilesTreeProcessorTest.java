package edu.hw9;

import edu.hw9.task2.FilesTreeProcessor;
import java.io.File;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FilesTreeProcessorTest {
    @Test
    public void searchDirectoriesWorksCorrectly(@TempDir File tempDir) {
        makeTestingDirectoriesStructure(tempDir);

        Assertions.assertThat(FilesTreeProcessor.searchDirectories(tempDir, 2))
            .containsExactlyInAnyOrder(
                new File(tempDir, "dir2"),
                new File(tempDir, "dir2/dir4")
            );
    }

    @Test
    public void searchDirectoriesWorksCorrectlyWithEmptyData() {
        Assertions.assertThat(FilesTreeProcessor.searchDirectories(null, 2)).isNull();
    }

    @Test
    public void filterFilesWorksCorrectly(@TempDir File tempDir) {
        makeTestingDirectoriesStructure(tempDir);

        Assertions
            .assertThat(FilesTreeProcessor.filterFilesRecursively(tempDir, file -> file.getName().endsWith(".txt")))
            .containsExactlyInAnyOrder(
                new File(tempDir, "dir2/file1.txt"),
                new File(tempDir, "dir3/file3.txt"),
                new File(tempDir, "dir2/dir4/file4.txt"),
                new File(tempDir, "dir2/dir4/file5.txt")
            );
    }

    @Test
    public void filterFilesWorksCorrectlyWithEmptyData() {
        Assertions.assertThat(
                FilesTreeProcessor.filterFilesRecursively(null, file -> file.getName().endsWith(".txt")))
            .isNull();
    }

    private static File createDirectory(File parent, String name) {
        var directory = new File(parent, name);
        directory.mkdir();
        return directory;
    }

    private static void makeTestingDirectoriesStructure(File directory) {
        var dir2 = createDirectory(directory, "dir2");
        var dir3 = createDirectory(directory, "dir3");
        createFile(dir2, "file1.txt");
        createFile(dir2, "file2.png");
        createFile(dir3, "file3.txt");

        var dir4 = createDirectory(dir2, "dir4");
        createFile(dir4, "file4.txt");
        createFile(dir4, "file5.txt");
    }

    private static File createFile(File parent, String fileName) {
        var directory = new File(parent, fileName);
        try {
            directory.createNewFile();
        } catch (IOException e) {
            return null;
        }

        return directory;
    }
}
