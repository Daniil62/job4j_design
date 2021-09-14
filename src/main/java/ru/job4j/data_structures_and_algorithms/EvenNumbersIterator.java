package ru.job4j.data_structures_and_algorithms;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalInt;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int i = index;
        boolean result = dataToStream().isPresent();
        index = i;
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        //noinspection OptionalGetWithoutIsPresent
        return dataToStream().getAsInt();
    }

    private OptionalInt dataToStream() {
        return Arrays.stream(data).skip(index).peek(n -> index++).filter(value -> value % 2 == 0).findFirst();
    }
}