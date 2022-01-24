package ru.job4j.chapter1.ood.srp.reports.utils;

import ru.job4j.chapter1.ood.srp.reports.model.Employee;

import java.util.Comparator;

public class DecreaseSalaryComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o2.getSalary(), o1.getSalary());
    }
}
