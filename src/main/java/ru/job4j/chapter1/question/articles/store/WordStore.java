package ru.job4j.chapter1.question.articles.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.chapter1.question.articles.model.Word;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WordStore implements Store<Word>, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordStore.class.getSimpleName());

    private final Properties properties;

    private Connection connection;

    public WordStore(Properties properties) {
        this.properties = properties;
        initConnection();
        initScheme();
        initWords();
    }

    private void initConnection() {
        LOGGER.info(Constants.InfoMassages.CONNECT_HEAD_MASSAGE);
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty(Constants.PropertyKeys.URL),
                    properties.getProperty(Constants.PropertyKeys.USERNAME),
                    properties.getProperty(Constants.PropertyKeys.PASSWORD)
            );
        } catch (SQLException e) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
    }

    private void initScheme() {
        LOGGER.info(Constants.InfoMassages.INIT_DB_SCHEME_HEAD_MESSAGE);
        try (var statement = connection.createStatement()) {
            var sql = Files.readString(Paths.get(Constants.Paths.INIT_DB_SCHEME_REQUEST_PATH));
            statement.execute(sql);
        } catch (Exception e) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
    }

    private void initWords() {
        LOGGER.info(Constants.InfoMassages.FILL_WORDS_TABLE_HEAD_MESSAGE);
        try (var statement = connection.createStatement()) {
            var sql = Files.readString(Paths.get(Constants.Paths.FILL_WORDS_DB_REQUEST_PATH));
            statement.executeLargeUpdate(sql);
        } catch (Exception e) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
    }

    @Override
    public Word save(Word model) {
        LOGGER.info(Constants.InfoMassages.SAVE_WORD_HEAD_MESSAGE);
        var sql = "insert into dictionary(word) values(?);";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getValue());
            statement.executeUpdate();
            var key = statement.getGeneratedKeys();
            if (key.next()) {
                model.setId(key.getInt(1));
            }
        } catch (Exception e) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
        return model;
    }

    @Override
    public List<Word> findAll() {
        LOGGER.info(Constants.InfoMassages.FIND_WORDS_HEAD_MESSAGE);
        var sql = "select * from dictionary";
        var words = new ArrayList<Word>();
        try (var statement = connection.prepareStatement(sql)) {
            var selection = statement.executeQuery();
            while (selection.next()) {
                words.add(new Word(
                        selection.getInt(Constants.SelectionColumns.ID),
                        selection.getString(Constants.SelectionColumns.WORD)
                ));
            }
        } catch (Exception e) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
        return words;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private static class Constants {

        static final class PropertyKeys {

            static final String URL = "url";
            static final String USERNAME = "username";
            static final String PASSWORD = "password";
        }

        static final class InfoMassages {

            static final String CONNECT_HEAD_MASSAGE = "Подключение к базе данных слов";
            static final String ERROR_MESSAGE = "Не удалось выполнить операцию: { }";
            static final String INIT_DB_SCHEME_HEAD_MESSAGE = "Создание схемы таблицы слов";
            static final String FILL_WORDS_TABLE_HEAD_MESSAGE = "Заполнение таблицы слов";
            static final String SAVE_WORD_HEAD_MESSAGE = "Добавление слова в базу данных";
            static final String FIND_WORDS_HEAD_MESSAGE = "Загрузка всех слов";
        }

        static final class Paths {

            static final String INIT_DB_SCHEME_REQUEST_PATH =
                    "./src/main/java/ru/job4j/chapter1/question/articles/db/dictionary.sql";
            static final String FILL_WORDS_DB_REQUEST_PATH =
                    "./src/main/java/ru/job4j/chapter1/question/articles/db/words.sql";
        }

        static final class SelectionColumns {

            static final String ID = "id";
            static final String WORD = "word";
        }
    }
}
