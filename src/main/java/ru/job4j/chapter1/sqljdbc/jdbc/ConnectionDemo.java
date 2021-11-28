package ru.job4j.chapter1.sqljdbc.jdbc;

import ru.job4j.chapter1.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {

    private static final String PATH = "./data/app.properties";
    private String url;
    private String driver;
    private String login;
    private String password;
    private Config config = new Config(PATH);

    public void connect() {
        setValues();
        setDriver(driver);
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setValues() {
        config.load();
        url = config.value(Keys.URL);
        driver = config.value(Keys.DRIVER);
        login = config.value(Keys.LOGIN);
        password = config.value(Keys.PASSWORD);
    }

    private void setDriver(String value) {
        try {
            Class.forName(value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final static class Keys {
        private static final String URL = "url";
        private static final String DRIVER = "driver";
        private static final String LOGIN = "login";
        private static final String PASSWORD = "password";
    }

    public static void main(String[] args) {
       new ConnectionDemo().connect();
    }
}
