package ru.job4j.data_structures_and_algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index = 0;

    public EvenNumbersIterator(final int[] array) {
        this.data = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        int tempIndex = index;
        while (tempIndex < data.length && !result) {
            result = data[tempIndex] % 2 == 0;
            ++tempIndex;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (index < data.length && data[index] % 2 != 0) {
            ++index;
        }
        int result = data[index];
        index++;
        return result;
    }
}