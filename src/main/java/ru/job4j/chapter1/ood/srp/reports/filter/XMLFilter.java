package ru.job4j.chapter1.ood.srp.reports.filter;

import ru.job4j.chapter1.ood.srp.reports.model.Employee;
import ru.job4j.chapter1.ood.srp.reports.model.Employees;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class XMLFilter implements Filter {

    @Override
    public String filter(List<Employee> list) {
        String result = "";
        if (list != null && list.size() > 0) {
            try {
                JAXBContext context = JAXBContext.newInstance(Employees.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                try (StringWriter writer = new StringWriter()) {
                    marshaller.marshal(new Employees(list), writer);
                    result = writer.getBuffer().toString();
                } catch (IOException | JAXBException e) {
                    e.printStackTrace();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
