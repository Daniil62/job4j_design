package ru.job4j.chapter1.ood.srp.reports.filter;

import ru.job4j.chapter1.ood.srp.reports.model.Employee;
import ru.job4j.chapter1.ood.srp.reports.utils.Fields;

import java.util.List;

public class AccountingFilter implements Filter {

    private String currencySymbol;

    public AccountingFilter(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    @Override
    public String filter(List<Employee> list) {
        StringBuilder builder = new StringBuilder();
        if (list != null && list.size() > 0) {
            builder.append(Fields.NAME)
                    .append(Fields.DELIMITER)
                    .append(Fields.HIRED)
                    .append(Fields.DELIMITER)
                    .append(Fields.FIRED)
                    .append(Fields.DELIMITER)
                    .append(Fields.SALARY)
                    .append(System.lineSeparator());
            for (Employee employee : list) {
                builder.append(employee.getName())
                        .append(Fields.DELIMITER)
                        .append(employee.getHired())
                        .append(Fields.DELIMITER)
                        .append(employee.getFired())
                        .append(Fields.DELIMITER)
                        .append(employee.getSalary())
                        .append(currencySymbol)
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
