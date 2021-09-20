package ru.job4j.chapter1.generic.animal;

import java.util.ArrayList;
import java.util.List;

public class Generics {

    private void printObject(List<?> list) {
        for (Object next : list) {
            System.out.println("Current element: " + next);
        }
    }

    private void printBoundedWildCard(List<? extends Predator> list) {
        for (Object next : list) {
            System.out.println("Current element: " + next);
        }
    }

    private void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Object next : list) {
            System.out.println("Current element: " + next);
        }
    }

    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();

        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

//        generics.printBoundedWildCard(first);      //  error: bounded from above
        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);
//        generics.printLowerBoundedWildCard(third); //  error: bounded from below
    }
}
