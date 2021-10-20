package ru.job4j.chapter1.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public static List<String> filter(String path) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            result = in.lines().filter(s -> {
                        String[] arr = s.split(" ");
                        return arr[arr.length - 2].equals("404");
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void showLog(List<String> log) {
        for (String s : log) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        List<String> log = LogFilter.filter("./txt_files/log.txt");
        LogFilter.showLog(log);
    }
}
