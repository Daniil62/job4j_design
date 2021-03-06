
package ru.job4j.chapter1.ood.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import ru.job4j.chapter1.ood.srp.reports.Report;
import ru.job4j.chapter1.ood.srp.reports.ReportEngine;
import ru.job4j.chapter1.ood.srp.reports.filter.*;
import ru.job4j.chapter1.ood.srp.reports.model.Employee;
import ru.job4j.chapter1.ood.srp.reports.store.MemStore;
import ru.job4j.chapter1.ood.srp.reports.utils.DecreaseSalaryComparator;

import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        Report engine = new ReportEngine(store);
        String expected = "Name | Hired | Fired | Salary"
                + System.lineSeparator()
                + worker.getName()
                + " | "
                + worker.getHired()
                + " | "
                + worker.getFired()
                + " | "
                + worker.getSalary()
                + System.lineSeparator();
        assertThat(engine.generate(em -> true, null), is(expected));
    }

    @Test
    public void whenHTMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        Report engine = new ReportEngine(store);
        String expected = "<tr><td><h3>Name<h3></td><td><h3>Hired<h3></td><td><h3>Fired<h3></td><td><h3>Salary<h3></td></tr>"
                + System.lineSeparator()
                + "<tr><td>"
                + worker.getName()
                + "</td><td>"
                + worker.getHired()
                + "</td><td>"
                + worker.getFired()
                + "</td><td>"
                + worker.getSalary()
                + "</td></tr>"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true, new HTMLFilter()), is(expected));
    }

    @Test
    public void whenDecreaseSalaryGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 300000);
        Employee worker2 = new Employee("Vladimir", now, now, 200000);
        Employee worker3 = new Employee("Daniil", now, now, 600000);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportEngine(store);
        String expected = "Name | Salary"
                + System.lineSeparator()
                + worker3.getName()
                + " | "
                + worker3.getSalary()
                + System.lineSeparator()
                + worker.getName()
                + " | "
                + worker.getSalary()
                + System.lineSeparator()
                + worker2.getName()
                + " | "
                + worker2.getSalary()
                + System.lineSeparator();
        assertThat(engine.generate(em -> true, new DecreaseSalaryFilter(new DecreaseSalaryComparator())), is(expected));
    }

    @Test
    public void whenAccountingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 5000);
        store.add(worker);
        Report engine = new ReportEngine(store);
        String expected = "Name | Hired | Fired | Salary"
                + System.lineSeparator()
                + worker.getName() + " | "
                + worker.getHired() + " | "
                + worker.getFired() + " | "
                + worker.getSalary() + "$"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true, new AccountingFilter("$")), is(expected));
    }

    @Test
    public void whenXMLFormatGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", null, null, 300000);
        Employee worker2 = new Employee("Vladimir", null, null, 200000);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportEngine(store);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employees>\n"
                + "    <employee>\n"
                + "        <name>Ivan</name>\n"
                + "        <salary>300000.0</salary>\n"
                + "    </employee>\n"
                + "    <employee>\n"
                + "        <name>Vladimir</name>\n"
                + "        <salary>200000.0</salary>\n"
                + "    </employee>\n"
                + "</employees>\n";
        assertThat(engine.generate(em -> true, new XMLFilter()), is(expected));
    }

    @Test
    public void whenJSONFormatGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", null, null, 300000);
        Employee worker2 = new Employee("Vladimir", null, null, 200000);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportEngine(store);
        String expected = "[{\"name\":\"Ivan\",\"salary\":300000.0},{\"name\":\"Vladimir\",\"salary\":200000.0}]";
        assertThat(engine.generate(em -> true, new JSONFilter()), is(expected));
    }

    @Test
    public void whenHaveNoEmployeesWithoutFilters() {
        MemStore store = new MemStore();
        Report engine = new ReportEngine(store);
        assertThat(engine.generate(em -> true, null), is(""));
    }

    @Test
    public void whenHaveNoEmployeesWithHTMLFilter() {
        MemStore store = new MemStore();
        Report engine = new ReportEngine(store);
        assertThat(engine.generate(em -> true, new HTMLFilter()), is(""));
    }

    @Test
    public void whenHaveNoEmployeesWithDecreaseSalaryFilter() {
        MemStore store = new MemStore();
        Report engine = new ReportEngine(store);
        assertThat(engine.generate(em -> true, new DecreaseSalaryFilter(new DecreaseSalaryComparator())), is(""));
    }

    @Test
    public void whenHaveNoEmployeesWithAccountingFilter() {
        MemStore store = new MemStore();
        Report engine = new ReportEngine(store);
        assertThat(engine.generate(em -> true, new AccountingFilter("$")), is(""));
    }

    @Test
    public void whenHaveNoEmployeesWithXMLFilter() {
        MemStore store = new MemStore();
        Report engine = new ReportEngine(store);
        assertThat(engine.generate(em -> true, new XMLFilter()), is(""));
    }

    @Test
    public void whenHaveNoEmployeesWithJSONFilter() {
        MemStore store = new MemStore();
        Report engine = new ReportEngine(store);
        assertThat(engine.generate(em -> true, new JSONFilter()), is(""));
    }
}