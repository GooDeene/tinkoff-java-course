package edu.hw3;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> internalCollection;
    private int cursor;

    public BackwardIterator(List<T> collection) {
        this.internalCollection = collection;
        this.cursor = collection.size();
    }

    @Override
    public boolean hasNext() {
        return cursor > 0;
    }

    @Override
    public T next() {
        cursor--;
        if (cursor < 0) {
            throw new NullPointerException();
        }
        return internalCollection.get(cursor);
    }
}
