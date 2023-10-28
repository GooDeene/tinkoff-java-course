package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

public class Task7Test {
    @Test
    public void correctWorksWithNullKey() {
        TreeMap<String, String> tree = new TreeMap<>(new NullAvailableComparator());
        tree.put(null, "test");

        Assertions.assertTrue(tree.containsKey(null));
        Assertions.assertEquals("test", tree.get(null));
    }
}
