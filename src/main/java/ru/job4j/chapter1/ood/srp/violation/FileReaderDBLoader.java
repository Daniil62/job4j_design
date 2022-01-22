package ru.job4j.chapter1.ood.srp.violation;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Пример нарушения принципа SRP.
 * Класс отвечает за чтение строки из файла
 * и за запись строки в базу данных.
 */
public class FileReaderDBLoader implements AntiSRPReaderLoader, Closeable {

    private Connection connection;
    private final static String PROPERTIES = "some.properties";
    private final static String DRIVER = "driver";
    private final static String URL = "url";
    private final static String USER = "user";
    private final static String PASSWORD = "password";

    /**
     * Второй пример нарушения принципа SRP.
     * Метод создает внутри объект класса
     * StringModifier.
     */
    @Override
    public String read(String url) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(url))) {
            reader.lines().forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new StringModifier().modify(builder.toString());
    }

    /**
     * третий пример нарушения принципа SRP.
     * Метод отвечает за создание соединения
     * и за запись строки в базу данных.
     */
    @Override
    public void load(String data) {
        try (InputStream in = FileReaderDBLoader.class.getClassLoader().getResourceAsStream(PROPERTIES)) {
            Properties properties = new Properties();
            if (in != null) {
                properties.load(in);
            }
            Class.forName(properties.getProperty(DRIVER));
            connection = DriverManager.getConnection(properties.getProperty(URL),
                    properties.getProperty(USER), properties.getProperty(PASSWORD)
            );
            try (PreparedStatement statement = connection.prepareStatement(
                    "insert into someTable(name) values(?)")) {
                statement.setString(1, data);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
