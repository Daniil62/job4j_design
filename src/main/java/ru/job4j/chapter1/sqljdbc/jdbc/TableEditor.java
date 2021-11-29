package ru.job4j.chapter1.sqljdbc.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;
    private Statement statement;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
        setStatement();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty(Keys.DRIVER));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty(Keys.URL),
                    properties.getProperty(Keys.LOGIN),
                    properties.getProperty(Keys.PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String name) {
        execute(String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s);",
                name, "id serial primary key"));
    }

    public void dropTable(String tableName) {
        execute(String.format("DROP TABLE IF EXIST %s", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        execute(String.format(
                "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s",
                tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        execute(String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        execute(String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format("select * from %s limit 1", tableName));
            var metaData = selection.getMetaData();
            for (int i = 1; i < metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n", metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    private void execute(String response) {
        try {
            if (statement != null) {
                statement.execute(response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    private static final class Keys {

        private static final String URL = "url";
        private static final String DRIVER = "driver";
        private static final String LOGIN = "login";
        private static final String PASSWORD = "password";
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        BufferedReader reader = new BufferedReader(new FileReader("./data/app.properties"));
        properties.load(reader);
        TableEditor editor = new TableEditor(properties);
        String tableName = "new_table";

        editor.createTable(tableName);
        System.out.println(editor.getTableScheme(tableName));

        editor.addColumn(tableName, "value", "varchar(255)");
        System.out.println(editor.getTableScheme(tableName));
    }
}
