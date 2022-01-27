package ru.job4j.chapter1.ood.ocp.violation;

import java.util.ArrayList;

public abstract class MusicGenre {

    /**
     * Третий пример нарушения принципа OCP.
     * Поле представляет собой реализацию
     * интерфейса List.
     */
    protected ArrayList<String> representatives;

    abstract String getInfo();
}
