package ru.job4j.chapter1.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt<T> implements Iterator<T> {

    private final T[][] data;
    private int row = 0;
    private int col = 0;

    public MatrixIt(T[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == col) {
            ++row;
            col = 0;
        }
        return row < data.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][col++];
    }
}
