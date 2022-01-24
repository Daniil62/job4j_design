package ru.job4j.chapter1.ood.srp.reports.filter;

import ru.job4j.chapter1.ood.srp.reports.model.Employee;
import ru.job4j.chapter1.ood.srp.reports.utils.Fields;

import java.util.List;

public class HTMLFilter implements Filter {

    private static final String EXTERNAL_OPEN_TAG_BLOCK = "<tr><td>";
    private static final String EXTERNAL_CLOSE_TAG_BLOCK = "</td></tr>";
    private static final String OPEN_TAG = "<td>";
    private static final String CLOSE_TAG = "</td>";
    private static final String H3 = "<h3>";

    @Override
    public String filter(List<Employee> list) {
        StringBuilder builder = new StringBuilder();
        if (list != null && list.size() > 0) {
            String mediumBlock = H3 + CLOSE_TAG + OPEN_TAG + H3;
            builder.append(EXTERNAL_OPEN_TAG_BLOCK)
                    .append(H3)
                    .append(Fields.NAME)
                    .append(mediumBlock)
                    .append(Fields.HIRED)
                    .append(mediumBlock)
                    .append(Fields.FIRED)
                    .append(mediumBlock)
                    .append(Fields.SALARY)
                    .append(H3)
                    .append(EXTERNAL_CLOSE_TAG_BLOCK)
                    .append(System.lineSeparator());
            for (Employee employee : list) {
                builder.append(EXTERNAL_OPEN_TAG_BLOCK)
                        .append(employee.getName())
                        .append(CLOSE_TAG)
                        .append(OPEN_TAG)
                        .append(employee.getHired())
                        .append(CLOSE_TAG)
                        .append(OPEN_TAG)
                        .append(employee.getFired())
                        .append(CLOSE_TAG)
                        .append(OPEN_TAG)
                        .append(employee.getSalary())
                        .append(EXTERNAL_CLOSE_TAG_BLOCK)
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
