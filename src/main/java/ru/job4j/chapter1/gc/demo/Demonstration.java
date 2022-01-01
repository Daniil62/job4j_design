package ru.job4j.chapter1.gc.demo;

public class Demonstration {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println();
        System.out.println(String.format("free: %d", freeMemory / MB));
        System.out.println(String.format("total: %d", totalMemory / MB));
        System.out.println(String.format("max: %d", maxMemory / MB));
    }

    public static void main(String[] args) {
        info();

        for (int i = 0; i < 9000; i++) {
            new User(i, "Ivan" + i, i);
        }

        info();
    }
}
