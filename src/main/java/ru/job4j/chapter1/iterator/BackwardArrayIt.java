package ru.job4j.chapter1.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt<T> implements Iterator<T> {

    private final T[] data;
    private int point;

    public BackwardArrayIt(T[] data) {
        this.data = data;
        point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}
