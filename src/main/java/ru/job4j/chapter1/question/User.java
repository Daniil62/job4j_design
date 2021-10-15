package ru.job4j.chapter1.question;

public class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this == o || id == user.id && name.equals(((User) o).name);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result *= 31 + name.hashCode();
        return result;
    }
}
