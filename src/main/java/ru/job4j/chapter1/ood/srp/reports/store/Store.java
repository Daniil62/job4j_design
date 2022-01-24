package ru.job4j.chapter1.ood.srp.reports.store;

import ru.job4j.chapter1.ood.srp.reports.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    List<Employee> findBy(Predicate<Employee> filter);
}