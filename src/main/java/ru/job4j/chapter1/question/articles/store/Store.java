package ru.job4j.chapter1.question.articles.store;

import java.util.List;

public interface Store<T> {

    T save(T model);
    List<T> findAll();
}
