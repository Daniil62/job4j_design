package ru.job4j.chapter1.ood.ocp.violation;

import java.util.ArrayList;
import java.util.Iterator;

public class DeathMetal extends MusicGenre {

    private static final String TITLE = "Death Metal.";
    private static final String ABOUT = "Some interesting information about this music genre...";
    private static final String REPRESENTATIVES_INTRO = "Bright representatives of this genre are: ";

    public void setRepresentatives(ArrayList<String> representatives) {
        this.representatives = representatives;
    }

    @Override
    String getInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(TITLE)
                .append(System.lineSeparator())
                .append(ABOUT)
                .append(System.lineSeparator())
                .append(REPRESENTATIVES_INTRO)
                .append(System.lineSeparator());
        Iterator<String> iterator = representatives.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next())
                    .append(iterator.hasNext() ? ", " : ".");
        }
        return builder.toString();
    }

}
