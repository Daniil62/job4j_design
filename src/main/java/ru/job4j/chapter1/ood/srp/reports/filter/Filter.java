package ru.job4j.chapter1.ood.srp.reports.filter;

import ru.job4j.chapter1.ood.srp.reports.model.Employee;

import java.util.List;

public interface Filter {

    String filter(List<Employee> list);
}
