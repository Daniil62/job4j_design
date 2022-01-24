package ru.job4j.chapter1.ood.srp.reports.filter;

import ru.job4j.chapter1.ood.srp.reports.model.Employee;
import ru.job4j.chapter1.ood.srp.reports.utils.Fields;

import java.util.Comparator;
import java.util.List;

public class DecreaseSalaryFilter implements Filter {

    private Comparator<Employee> comparator;

    public DecreaseSalaryFilter(Comparator<Employee> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String filter(List<Employee> list) {
        StringBuilder builder = new StringBuilder();
        if (list != null && list.size() > 0) {
            list.sort(comparator);
            builder.append(Fields.NAME)
                    .append(Fields.DELIMITER)
                    .append(Fields.SALARY)
                    .append(System.lineSeparator());
            for (Employee employee : list) {
                builder.append(employee.getName())
                        .append(Fields.DELIMITER)
                        .append(employee.getSalary())
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
