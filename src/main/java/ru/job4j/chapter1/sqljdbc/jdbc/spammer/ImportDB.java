package ru.job4j.chapter1.sqljdbc.jdbc.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties properties;
    private String dump;

    public ImportDB(Properties properties, String dump) {
        this.properties = properties;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(s -> users.add(parse(s)));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty(Keys.DRIVER));
        try (Connection connection = DriverManager.getConnection(
                properties.getProperty(Keys.URL),
                properties.getProperty(Keys.LOGIN),
                properties.getProperty(Keys.PASSWORD)
        )) {
            for (User user : users) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "insert into users(name, email) values(?, ?)")) {
                    statement.setString(1, user.name);
                    statement.setString(2, user.email);
                    statement.execute();
                }
            }
        }
    }

    private User parse(String line) {
        String[] values = line.split(";");
        validate(values);
        return new User(values[0], values[1]);
    }

    private void validate(String[] values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("Missing arguments!");
        }
    }

    private static class User {

        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    private static class Keys {
        private static final String URL = "jdbc.url";
        private static final String DRIVER = "jdbc.driver";
        private static final String LOGIN = "login";
        private static final String PASSWORD = "password";
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("./data/app.properties")) {
            properties.load(in);
        }

        ImportDB db = new ImportDB(properties, "./txt_files/dump.txt");
        db.save(db.load());
    }
}
