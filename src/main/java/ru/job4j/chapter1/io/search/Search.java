package ru.job4j.chapter1.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    public void showResult(Path root, Predicate<Path> condition) {
        search(root, condition).forEach((System.out::println));
    }

    public static void main(String[] args) {
        new Search().showResult(Paths.get("."), f -> f.toFile().getName().endsWith("js"));
    }
}
