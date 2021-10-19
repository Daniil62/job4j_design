package ru.job4j.chapter1.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    private String scanFile() {
        StringBuilder builder = new StringBuilder();
        try (FileInputStream in = new FileInputStream("C:\\projects\\job4j_design\\txt_files\\even.txt")) {
            int numb;
            while ((numb = in.read()) != -1) {
                builder.append((char) numb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void analyse() {
        String[] lines = scanFile().split("\\D+");
        for (String s : lines) {
            System.out.println(Integer.parseInt(s) % 2 == 0);
        }
    }

    public static void main(String[] args) {
        new EvenNumberFile().analyse();
    }
}
