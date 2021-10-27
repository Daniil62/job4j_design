package ru.job4j.chapter1.io.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public List<Path> search(String[] arguments, Predicate<Path> condition) {
        validate(arguments);
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(Paths.get(arguments[0]), searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    public void showResult(String[] arguments, Predicate<Path> condition) {
        search(arguments, condition).forEach((System.out::println));
    }

    private void validate(String[] arguments) {
        if (arguments.length < 2) {
            throw new IllegalArgumentException("Root folder or file extension is null."
                    + " Usage java -jar dir.jar ROOT_FOLDER and FILE_EXTENSION");
        }
        String firstArgument = arguments[0];
        File file = Paths.get(firstArgument).toFile();
        if (!file.exists() && !file.isDirectory()) {
            throw new IllegalArgumentException("Specified root folder \""
                    + firstArgument + "\" not exist. Check the java -jar dir.jar ROOT_FOLDER");
        }
    }

    public static void main(String[] args) {
        new Search().showResult(args, f -> f.toFile().getName().endsWith(args[1]));
    }
}

