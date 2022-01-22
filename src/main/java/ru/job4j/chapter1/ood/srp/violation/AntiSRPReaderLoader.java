package ru.job4j.chapter1.ood.srp.violation;

public interface AntiSRPReaderLoader {

    String read(String url);
    void load(String data);
}