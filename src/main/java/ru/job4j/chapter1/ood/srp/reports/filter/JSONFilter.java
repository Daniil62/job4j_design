package ru.job4j.chapter1.ood.srp.reports.filter;

import com.google.gson.GsonBuilder;
import ru.job4j.chapter1.ood.srp.reports.model.Employee;

import java.util.List;

public class JSONFilter implements Filter {

    @Override
    public String filter(List<Employee> list) {
        return list != null && list.size() > 0 ? new GsonBuilder().create().toJson(list) : "";
    }
}
