package ru.job4j.chapter1.serialization;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ContactTest {

    private Contact contact;

    @Before
    public void init() {
        contact = new Contact(123456, "+7 (495) 000-00-00");
    }

    @Test
    public void whenWriteToFileSuccess() {
        assertNotNull(contact.writeToFile());
    }

    @Test
    public void whenReadFromFileSuccess() {
        File file = contact.writeToFile();
        Contact contactFromFile = contact.readFromFile(file);
        assertEquals(contact, contactFromFile);
    }

}
