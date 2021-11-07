package ru.job4j.chapter1.serialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.chapter1.logging.UsageLog4j;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    private int zipCode;
    private String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public File writeToFile() {
        File result = null;
        try {
            result = Files.createTempFile(null, null).toFile();
        } catch (IOException e) {
            LOG.error("Creating file error: ", e);
        }
        return write(result);
    }

    public Contact readFromFile(File file) {
        Contact result = null;
        try (FileInputStream fileInput = new FileInputStream(file);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            try {
                result = (Contact) objectInput.readObject();
            } catch (ClassNotFoundException e) {
                LOG.error("Class not found: ", e);
            }
        } catch (IOException e) {
            LOG.error("Reading file error: ", e);
        }
        return result;
    }

    private File write(File file) {
        if (file != null) {
            try (FileOutputStream fileOutput = new FileOutputStream(file);
                 ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
                objectOutput.writeObject(this);
            } catch (IOException e) {
                LOG.error("Write to file error: ", e);
            }
        }
        return file;
    }

    @Override
    public String toString() {
        return "Contact {" + System.lineSeparator()
                + "zipCode=" + zipCode + "," + System.lineSeparator()
                + "phone='" + phone + '\'' + System.lineSeparator() + '}'
                + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode
                && phone.equals(contact.phone);
    }

    @Override
    public int hashCode() {
        int result = phone.hashCode();
        result *= 31 + Integer.hashCode(zipCode);
        return result;
    }

    public static void main(String[] args) {

        final Contact contact = new Contact(123456, "+7 (495) 000-00-00");

        Contact contactFromFile = contact.readFromFile(contact.writeToFile());
        System.out.println(contactFromFile);
    }
}
