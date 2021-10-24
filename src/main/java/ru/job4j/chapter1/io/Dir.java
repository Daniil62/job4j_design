package ru.job4j.chapter1.io;

import java.io.File;
import java.util.Objects;

public class Dir {

    public void showFileInfo(String path) {
        File file = new File(path);
        checkFile(file);
        System.out.println(fileInfo(file));
    }

    private void checkFile(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    private String fileInfo(File file) {
        StringBuilder builder = new StringBuilder();
        for (File f : Objects.requireNonNull(file.listFiles())) {
            builder.append(String.format("File name: %s", f.getName()))
                    .append(System.lineSeparator())
                    .append(String.format("File size: %s bytes", f.length()))
                    .append(System.lineSeparator())
                    .append("______________________________")
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        new Dir().showFileInfo("./");
    }
}
