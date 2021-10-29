package ru.job4j.chapter1.io;

import ru.job4j.chapter1.io.search.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private final Map<String, String> values = new HashMap<>();

    public void packFiles(String[] args) {
        parse(args);
        String excludedExtension = values.get(Keys.EXC);
        List<Path> sources = new Search().search(
                new String[]{values.get(Keys.DIR), excludedExtension},
                f -> !f.toFile().getName().endsWith(excludedExtension.substring(1)));
        toArchive(sources);
    }

    private void toArchive(List<Path> sources) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new
                FileOutputStream(Paths.get(values.get(Keys.OUT)).toFile())))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse(String[] args) {
        primaryValidate(args);
        for (String s : args) {
            secondaryValidate(s);
            String[] pair = s.split("=");
            values.put(pair[0], pair[1]);
        }
    }

    private void primaryValidate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException(Messages.MISSING_ARGS_ERR_MESSAGE);
        }
    }

    private void secondaryValidate(String argument) {
        if (!argument.matches("-[deo]=[^=]+")) {
            throw new IllegalArgumentException(Messages.INCORRECT_ARGS_ERR_MESSAGE);
        }
    }

    private static class Keys {

        private static final String DIR = "-d";
        private static final String EXC = "-e";
        private static final String OUT = "-o";
    }

    private static class Messages {

        private static final String MISSING_ARGS_ERR_MESSAGE = "Missing arguments! Arguments must contains: "
                + Keys.DIR + "=ROOT_DIRECTORY "
                + Keys.EXC + "=EXCLUDED_FILE_EXTENSION "
                + Keys.OUT + "=OUTPUT_ZIP_FILE.";

        private static final String INCORRECT_ARGS_ERR_MESSAGE = "key or value is incorrect (maybe null), "
                + "or missing beginning symbol \"-\" or/and combining symbol \"=\"."
                + System.lineSeparator() + "Use \""
                + Keys.DIR + "\" (root directory), \""
                + Keys.EXC + "\" (excluded extension), \""
                + Keys.OUT + "\" (output file) " + "symbols for keys and follow \"-key=value\" pattern only."
                + System.lineSeparator()
                + "Use spaces only between arguments and use only one \"=\" symbol in each of arguments).";
    }

    public static void main(String[] args) {
        new Zip().packFiles(args);
    }
}
