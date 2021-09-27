package ru.job4j.chapter1.list.linked;

public interface List<E> extends Iterable<E> {

    void add(E value);
    E get(int index);
}
