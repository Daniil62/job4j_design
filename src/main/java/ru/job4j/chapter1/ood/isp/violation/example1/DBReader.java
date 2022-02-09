package ru.job4j.chapter1.ood.isp.violation.example1;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBReader implements AntiISPReader {

    private Connection connection;
    private static final String PATH = "some.properties";
    private static final String ERROR_MESSAGE = "This class don`t read files and sites.";

    public DBReader() {
        init();
    }

    @Override
    public String readDB() {
        StringBuilder builder = new StringBuilder();
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from someDB")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                builder.append(rs.getString(1))
                        .append(", ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    @Override
    public String readFile(String file) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public String readWeb(String link) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    private void init() {
        try (InputStream in = DBReader.class.getClassLoader().getResourceAsStream(PATH)) {
            Properties config = new Properties();
            if (in != null) {
                config.load(in);
            }
            Class.forName(config.getProperty(Keys.DRIVER));
            connection = DriverManager.getConnection(config.getProperty(Keys.URL),
                    config.getProperty(Keys.USER), config.getProperty(Keys.PASSWORD)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Keys {
        static final String DRIVER = "some_driver";
        static final String URL = "some_url";
        static final String USER = "some_user";
        static final String PASSWORD = "some_password";
    }
}
