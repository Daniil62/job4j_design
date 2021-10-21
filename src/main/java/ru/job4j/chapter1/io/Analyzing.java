package ru.job4j.chapter1.io;

import java.io.*;
import java.util.function.Predicate;

public class Analyzing {

    private Predicate<String> threshold = s -> s.compareTo("300") > 0;
    private int count = 0;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            StringBuilder builder = new StringBuilder();
            in.lines().map(s -> s.split(" "))
                    .filter(arr -> threshold.test(arr[0]))
                    .forEach(arr -> {
                        String value = arr[1] + ";";
                        builder.append(++count % 2 == 0 ? value + System.lineSeparator() : value);
                        threshold = arr[0].compareTo("300") > 0
                                ? s -> s.compareTo("400") < 0
                                : s -> s.compareTo("300") > 0;
                    });
            printResult(builder.toString(), target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printResult(String log, String path) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
            out.println(log);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analyzing().unavailable("./data/csv_files/source_log.csv",
                "./data/csv_files/target_log.csv");
    }
}
