package ru.job4j.chapter1.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private transient Object[] container = new Object[10];
    private int size, point, modCount = 0;

    public SimpleArrayList(Collection<T> collection) {
        for (T element : collection) {
            add(element);
        }
    }

    public SimpleArrayList(int size) {
        this.container = new Object[size];
    }

    public SimpleArrayList() {
    }

    @Override
    public void add(T value) {
        add(value, container, size);
        ++size;
        ++modCount;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        Object result = container[index];
        container[index] = newValue;
        ++modCount;
        return (T) result;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        Object result = container[index];
        System.arraycopy(container, index + 1, container, index, size - 1);
        --size;
        ++modCount;
        return (T) result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModeCount = modCount;
            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[point++];
            }
        };
    }

    private void add(T value, Object[] array, int s) {
        if (array.length == s) {
            array = grow();
        }
        array[s] = value;
    }

    private Object[] grow() {
        container = Arrays.copyOf(container, size * 2);
        return container;
    }
}
