package ru.job4j.chapter1.ood.srp.reports.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

    @XmlElement(name = "employee")
    private List<Employee> list;

    public Employees(List<Employee> list) {
        this.list = list;
    }

    public Employees() {
    }

    public List<Employee> getList() {
        return list;
    }
}
