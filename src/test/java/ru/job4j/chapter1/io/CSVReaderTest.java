package ru.job4j.chapter1.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;

public class CSVReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenFilterTwoColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        new CSVReader().handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMissingArgument() throws Exception {
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=file.csv", "-out=stdout", "-filter=name,age"
        });
        new CSVReader().handle(argsName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectSourceFile() throws Exception {
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=file.csv", "-delimiter=;", "-out=stdout", "-filter=name,age"
        });
        new CSVReader().handle(argsName);
    }
}