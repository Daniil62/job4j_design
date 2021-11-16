package ru.job4j.chapter1.examples.filemanipulations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileMaster {

    public File createFile(String file) {
        File result = Paths.get(file).toFile();
        try {
            if (!result.exists()) {
                Files.createFile(result.toPath());
            } else {
                System.out.println(String.format("File %s already exist.", result.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void copy(Path file, Path destPath) {
        try {
            Files.copy(file, destPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(Path file, Path newDirectory) {
        try {
            Files.move(file, newDirectory, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(File file, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(File file) {
        if (file.exists() && file.delete()) {
            System.out.println("File deleted.");
        }
    }

    public void delete(Path path) {
        try {
            if (Files.deleteIfExists(path)) {
                System.out.println("File deleted.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        FileMaster master = new FileMaster();

        String sourceDirectory = ".\\src\\main\\java\\ru\\job4j\\chapter1\\examples\\filemanipulations\\src\\";
        String targetDirectory = ".\\src\\main\\java\\ru\\job4j\\chapter1\\examples\\filemanipulations\\target\\";

        File newFile = master.createFile(sourceDirectory + "new_file.txt");
        File moveFile = master.createFile(sourceDirectory + "file_for_moving.txt");
        File delFile = master.createFile(sourceDirectory + "deleted.txt");

        master.createFile(sourceDirectory + "second_deleted.txt");

        master.writeToFile(moveFile, "This file should be moved.");

        master.copy(newFile.toPath(), Paths.get(targetDirectory + "new_file.txt"));

        master.move(Paths.get(moveFile.getPath()), Paths.get(targetDirectory + "moved.txt"));

        master.delete(delFile);
        master.delete(Paths.get(sourceDirectory + "second_deleted.txt"));
    }
}
