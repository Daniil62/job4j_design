package ru.job4j.chapter1.gc.references.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;
    private static final String PATH_WORD = "Path";
    private static final String DIR_WORD = "Directory";
    private static final String NOT_EXIST = "not exist!";

    public DirFileCache(String cachingDir) {
        directoryValidate(cachingDir);
        this.cachingDir = cachingDir;
    }

    @Override
    public String load(String key) {
        fileValidate(cachingDir + key);
        String result = null;
        try {
            result = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void directoryValidate(String directory) {
        if (directory == null || !Paths.get(directory).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("%s \"%s\" %s", DIR_WORD, directory, NOT_EXIST));
        }
    }

    private void fileValidate(String path) {
        if (path == null || !Paths.get(path).toFile().exists()) {
            throw new IllegalArgumentException(String.format("%s \"%s\" %s", PATH_WORD, path, NOT_EXIST));
        }
    }
}