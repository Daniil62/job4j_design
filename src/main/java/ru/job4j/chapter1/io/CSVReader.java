package ru.job4j.chapter1.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    private final List<String> lines = new ArrayList<>();
    private String path;
    private String delimiter;
    private String filter;
    private String out;

    public void handle(ArgsName argsName) throws Exception {
        initValues(argsName);
        validate();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            if (lines.size() > 0) {
                print(generateResult(indexingFilteredElements(lines.get(0), filter)), out);
            }
        }
    }

    private void initValues(ArgsName argsName) {
        path = argsName.get(Keys.PATH);
        delimiter = argsName.get(Keys.DELIMITER);
        filter = argsName.get(Keys.FILTER);
        out = argsName.get(Keys.OUT);
    }

    private List<Integer> indexingFilteredElements(String firstLine, String filtered) {
        String[] filteredArray = filtered.split(",");
        List<String> headers = Arrays.asList(firstLine.split(delimiter));
        List<Integer> result = new ArrayList<>();
        for (String value : filteredArray) {
            result.add(headers.indexOf(value));
        }
        return result;
    }

    private void validate() {
        if (path == null || delimiter == null || filter == null || out == null) {
            throw new IllegalArgumentException(Errors.MISSING_ARGUMENTS);
        }
        if (!Paths.get(path).toFile().exists()) {
            throw new IllegalArgumentException(Errors.INVALID_SRC_FILE);
        }
    }

    private String generateResult(List<Integer> indexes) {
        StringBuilder builder = new StringBuilder();
        String field;
        for (int j = 0; j < lines.size(); j++) {
            int i = 0;
            Scanner scanner = new Scanner(lines.get(j)).useDelimiter(delimiter);
            for (; scanner.hasNext(); i++) {
                field = scanner.next();
                if (indexes.contains(i)) {
                    if (i > 0) {
                        builder.append(delimiter);
                    }
                    builder.append(field);
                }
            }
            if (j < lines.size() - 1) {
                builder.append(System.lineSeparator());
            }
            scanner.close();
        }
        return builder.toString();
    }

    private void print(String data, String targetFile) {
        if (Keys.STANDARD_OUTPUT.equals(targetFile)) {
            System.out.println(data);
        } else {
            try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(targetFile)))) {
                writer.println(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Keys {

        private static final String PATH = "path";
        private static final String DELIMITER = "delimiter";
        private static final String OUT = "out";
        private static final String FILTER = "filter";
        private static final String STANDARD_OUTPUT = "stdout";
    }

    private static class Errors {

        private static final String INVALID_SRC_FILE = "Invalid source file.";
        private static final String MISSING_ARGUMENTS = "Missing arguments.";
    }

    public static void main(String[] args) throws Exception {
        new CSVReader().handle(ArgsName.of(args));
    }
}
