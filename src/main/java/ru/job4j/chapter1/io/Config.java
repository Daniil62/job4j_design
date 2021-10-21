package ru.job4j.chapter1.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            AtomicInteger row = new AtomicInteger(1);
            read.lines()
                    .map(s -> s.split("="))
                    .filter(arr -> {
                        checkForIAException(!arr[0].startsWith("#") && arr.length < 2 && !arr[0].isEmpty(), row);
                        return !arr[0].startsWith("#") && arr.length == 2;
                    }).forEach(arr -> {
                        values.put(arr[0], arr[1]);
                        row.incrementAndGet();
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public int size() {
        return values.size();
    }

    private void checkForIAException(boolean isItError, AtomicInteger rowNumber) {
        if (isItError) {
            throw new IllegalArgumentException(path + ": incorrect file! Error in row "
                    + rowNumber + ". Properties must follow \"key=value\" pattern."
                    + System.lineSeparator());
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties"));
    }
}
