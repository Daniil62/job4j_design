package ru.job4j.chapter1.list.set;

import ru.job4j.chapter1.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> container = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        container.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T e : container) {
            if (Objects.equals(e, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
