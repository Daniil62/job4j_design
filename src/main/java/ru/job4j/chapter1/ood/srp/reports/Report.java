package ru.job4j.chapter1.ood.srp.reports;

import ru.job4j.chapter1.ood.srp.reports.filter.Filter;
import ru.job4j.chapter1.ood.srp.reports.model.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> predicate, Filter filter);
}