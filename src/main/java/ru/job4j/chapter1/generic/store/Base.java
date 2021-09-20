package ru.job4j.chapter1.generic.store;

public abstract class Base {

    private final String id;

    public Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
