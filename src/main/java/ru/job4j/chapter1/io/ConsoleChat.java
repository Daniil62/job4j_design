package ru.job4j.chapter1.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String outputLogFilePath;
    private final String botAnswersFilePath;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.outputLogFilePath = path;
        this.botAnswersFilePath = botAnswers;
    }

    public void run() {
        List<String> botLexicon = readPhrases(botAnswersFilePath);
        String message = "";
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean isItMonologue = false;
        while (!message.equals(OUT)) {
            message = scanner.nextLine();
            log.add(message);
            if (message.equals(STOP)) {
                isItMonologue = true;
            }
            if (message.equals(CONTINUE)) {
                isItMonologue = false;
            }
            if (botLexicon.size() > 0 && !isItMonologue && !message.equals(OUT)) {
                message = botLexicon.get((int) (Math.random() * botLexicon.size()));
                System.out.println(message);
                log.add(message);
            }
        }
        saveLog(log);
        scanner.close();
    }

    private List<String> readPhrases(String file) {
        validate(file);
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file, Charset.forName("WINDOWS-1251")))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(outputLogFilePath, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat("./chat.txt", "./txt_files/bot_lexicon.txt");
        chat.run();
    }
}
