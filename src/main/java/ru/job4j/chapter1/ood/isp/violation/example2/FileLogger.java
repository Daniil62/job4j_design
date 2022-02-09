package ru.job4j.chapter1.ood.isp.violation.example2;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class FileLogger implements AntiISPWriter<String> {

    private final static String FILE = "./values";
    private final static int MIN_VALUES_COUNT = 1;
    private static final String ERROR_MESSAGE = "Class has not access to data bases";

    @Override
    public void writeInDB(List<String> values) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void writeToFile(List<String> values) {
        validate(values);
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(FILE)))) {
            values.forEach(out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void validate(List<String> values) {
        if (values == null || values.size() < MIN_VALUES_COUNT) {
            throw new IllegalArgumentException();
        }
    }
}
