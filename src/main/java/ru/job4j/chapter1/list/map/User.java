package ru.job4j.chapter1.list.map;

import java.util.Calendar;
import java.util.Objects;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = result * 31 + Objects.hashCode(children);
        result = result * 31 + Objects.hashCode(birthday);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        return ((User) obj).name.equals(name)
                && ((User) obj).children == children
                && ((User) obj).birthday.getTime().equals(birthday.getTime());
    }
}
