package ru.job4j.chapter1.gc.demo;

public class User {

    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(String.format("%d %s, %d erased by garbage collector", id, name, age));
    }
}
