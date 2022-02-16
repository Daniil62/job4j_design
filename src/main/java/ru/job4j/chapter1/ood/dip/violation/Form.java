package ru.job4j.chapter1.ood.dip.violation;

import java.util.HashMap;
import java.util.Set;

public abstract class Form {

    protected final String name;

    /**
     * Пример нарушения DIP.
     * Поле является реализацией
     * а не абстракцией.
     */
    protected HashMap<String, String> fields = new HashMap<>();

    protected Form(String name, Set<String> fieldNames) {
        this.name = name;
        fieldNames.forEach(key -> fields.put(key, ""));
    }

    abstract Iterable<String> getFields();

    abstract boolean fill(String field, String value);

    abstract String getCompleteForm();
}
