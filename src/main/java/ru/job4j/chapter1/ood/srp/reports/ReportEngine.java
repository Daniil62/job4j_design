package ru.job4j.chapter1.ood.srp.reports;

import ru.job4j.chapter1.ood.srp.reports.filter.Filter;
import ru.job4j.chapter1.ood.srp.reports.model.Employee;
import ru.job4j.chapter1.ood.srp.reports.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {
    
    private Store store;
    private static final String HEADER = "Name | Hired | Fired | Salary";
    private static final String DELIMITER = " | ";


    public ReportEngine(Store store) {
        this.store = store;
    }

    private String defaultGenerate(Predicate<Employee> predicate) {
        StringBuilder text = new StringBuilder();
        List<Employee> list = store.findBy(predicate);
        if (list.size() > 0) {
            text.append(HEADER)
                    .append(System.lineSeparator());
            for (Employee employee : list) {
                text.append(employee.getName())
                        .append(DELIMITER)
                        .append(employee.getHired())
                        .append(DELIMITER)
                        .append(employee.getFired())
                        .append(DELIMITER)
                        .append(employee.getSalary())
                        .append(System.lineSeparator());
            }
        }
        return text.toString();
    }

    @Override
    public String generate(Predicate<Employee> predicate, Filter filter) {
        return filter != null ? filter.filter(store.findBy(predicate)) : defaultGenerate(predicate);
    }
}