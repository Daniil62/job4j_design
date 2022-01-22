package ru.job4j.chapter1.ood.srp.violation;

public interface Modifier<T> {

    T modify(T value);
}
