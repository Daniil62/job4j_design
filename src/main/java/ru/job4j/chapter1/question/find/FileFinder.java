package ru.job4j.chapter1.question.find;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.chapter1.io.ArgsName;
import ru.job4j.chapter1.io.search.SearchFiles;
import ru.job4j.chapter1.logging.UsageLog4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileFinder {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    private Predicate<Path> condition;
    ArgsName valuesHolder;

    public void find(String[] arguments) {
        valuesHolder = ArgsName.of(arguments);
        validate();
        setCondition();
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(Paths.get(valuesHolder.get(Keys.DIRECTORY)), searcher);
        } catch (IOException e) {
            LOG.error(Messages.DIR_NOT_FOUND_MSG, e);
        }
        writeResult(searcher.getPaths());
    }

    private void writeResult(List<Path> files) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(valuesHolder.get(Keys.OUTPUT))))) {
            files.forEach(out::println);
        } catch (FileNotFoundException e) {
            LOG.error(Messages.FILE_WRITING_ERROR_MSG, e);
        }
    }

    private void setCondition() {
        String searchType = valuesHolder.get(Keys.SEARCH_TYPE);
        String search = valuesHolder.get(Keys.SEARCH);
        if (searchType == null || searchType.equals(SearchParams.NAME)) {
            condition = f -> f.toFile().getName().equals(search);
        } else if (searchType.equals(SearchParams.MASK) || searchType.equals(SearchParams.REGEX)) {
            Pattern pattern = searchType.equals(SearchParams.MASK)
                    ? Pattern.compile(createMask(search)) : Pattern.compile(search);
            condition = f -> pattern.matcher(f.toFile().getName()).matches();
        }
    }

    private void validate() {
        if (valuesHolder.get(Keys.DIRECTORY) == null
                || valuesHolder.get(Keys.SEARCH) == null || valuesHolder.get(Keys.OUTPUT) == null) {
            throw new IllegalArgumentException(Messages.MISSING_ARGUMENTS_MSG);
        }
    }

    private String createMask(String searchType) {
        return searchType
                .replace("\\.", "[.]")
                .replace("?", "[^\\/,:*?\"<>|]")
                .replace("*", "[^\\/,:*?\"<>|]+");
    }

    private static class Keys {

        private final static String DIRECTORY = "d";
        private final static String SEARCH = "n";
        private final static String SEARCH_TYPE = "t";
        private final static String OUTPUT = "o";
    }

    private static class SearchParams {

        private final static String NAME = "name";
        private final static String MASK = "mask";
        private final static String REGEX = "regex";
    }

    private static class Messages {

        private final static String DIR_NOT_FOUND_MSG = "Error: directory not found. Check key and directory name, "
                + "arguments must follow \"-key=value\" pattern only";
        private final static String FILE_WRITING_ERROR_MSG = "Error: can not write the file";
        private static final String MISSING_ARGUMENTS_MSG = "Error:  one or more arguments missing, arguments must"
                + " contains directory name, search parameters, result file name and follow \"-key=value\" pattern only";
    }

    public static void main(String[] args) {
        new FileFinder().find(args);
    }
}
