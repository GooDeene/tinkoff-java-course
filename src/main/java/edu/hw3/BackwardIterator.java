package edu.hw3;

import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<T> implements Iterator<T> {
    private final T[] internalCollection;
    private int cursor;

    public BackwardIterator(Collection<T> collection) {
        this.internalCollection = (T[]) collection.toArray();
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
        return internalCollection[cursor];
    }
}
