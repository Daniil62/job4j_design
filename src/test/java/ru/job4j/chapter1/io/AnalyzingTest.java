package ru.job4j.chapter1.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnalyzingTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenFileReadSuccessful() throws IOException {
        File target = folder.newFile("target.csv");
        new Analyzing().unavailable("./data/csv_files/source_log.csv", target.getAbsolutePath());
        assertThat(readFile(target.getAbsolutePath()), is("10:57:01;10:59:01;11:01:02;11:02:02;"));
    }

    private String readFile(String target) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
