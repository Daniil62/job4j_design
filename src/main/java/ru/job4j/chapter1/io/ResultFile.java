package ru.job4j.chapter1.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {

    private String buildTable(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < size + 1; i++) {
            for (int j = 2; j < size + 1; j++) {
                builder.append(i)
                        .append(" x ")
                        .append(j)
                        .append(" = ")
                        .append((j) * (i))
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    public void createTextTable(int size) {
        try (FileOutputStream out = new FileOutputStream("C:\\projects\\job4j_design\\result.txt")) {
            out.write(buildTable(size).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResultFile resultFile = new ResultFile();
        resultFile.createTextTable(9);
    }
}
