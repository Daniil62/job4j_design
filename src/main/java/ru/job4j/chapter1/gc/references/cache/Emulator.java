package ru.job4j.chapter1.gc.references.cache;

import java.util.Scanner;

public class Emulator {

    private AbstractCache<String, String> cache;

    private void setDirectory(String directory) {
        cache = new DirFileCache(directory);
    }

    public void cache(String key) {
        cache.put(key, cache.load(key));
    }

    public String get(String key) {
        return cache.get(key);
    }

    private void showMenu() {
        System.out.println(Phrase.MENU
                        + System.lineSeparator()
                        + Phrase.FIRST_MENU
                        + System.lineSeparator()
                        + Phrase.SECOND_MENU
                        + System.lineSeparator()
                        + Phrase.THIRD_MENU
                        + System.lineSeparator()
                        + Phrase.FOURTH_MENU
                        + System.lineSeparator());
    }

    public void begin() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        int command = 0;
        while (run) {
            showMenu();
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Phrase.NOTE_1);
            }
            switch (command) {
                case 1:
                    System.out.println(Phrase.HINT_1);
                    try {
                        setDirectory(scanner.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println(Phrase.NOTE_2);
                    }
                    break;

                case 2:
                    System.out.println(Phrase.HINT_2_3);
                    cache(scanner.nextLine());
                    break;

                case 3:
                    System.out.println(Phrase.HINT_2_3);
                    try {
                        System.out.println(get(scanner.nextLine()));
                    } catch (NullPointerException e) {
                        System.out.println(Phrase.NOTHING_FOUND);
                    }
                    break;

                case 4:
                    System.out.println(Phrase.EXIT);
                    run = false;
                    break;

                default: System.out.println(Phrase.DEFAULT_NOTE);
            }
        }
        scanner.close();
    }

    private static class Phrase {

        static final String MENU = "__ MENU __";
        static final String FIRST_MENU = "1 - set directory";
        static final String SECOND_MENU = "2 - cache";
        static final String THIRD_MENU = "3 - get";
        static final String FOURTH_MENU = "4 - exit";
        static final String NOTE_1 = "You`ve entered some nonsense.";
        static final String NOTE_2 = "This directory is not exist.";
        static final String DEFAULT_NOTE = "Incorrect command.";
        static final String HINT_1 = "Enter directory: ";
        static final String HINT_2_3 = "Enter key: ";
        static final String NOTHING_FOUND = "Nothing found.";
        static final String EXIT = "Exit";
    }

    public static void main(String[] args) {

        new Emulator().begin();

    }
}
