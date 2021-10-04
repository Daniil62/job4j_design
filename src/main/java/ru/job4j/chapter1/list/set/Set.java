package ru.job4j.chapter1.list.set;

public interface Set<T> extends Iterable<T> {

    boolean add(T value);
    boolean contains(T value);
}
