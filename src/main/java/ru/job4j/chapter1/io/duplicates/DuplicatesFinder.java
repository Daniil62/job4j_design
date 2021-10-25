package ru.job4j.chapter1.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {

    public List<FileProperty> findDuplicates(Path path) {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        try {
            Files.walkFileTree(path, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitor.getDuplicates();
    }

    public void showDuplicates(Path path) {
        findDuplicates(path).stream().map(FileProperty::getName).forEach(System.out::println);
    }

    public static void main(String[] args) {
        new DuplicatesFinder().showDuplicates(Path.of("./"));
    }
}
