package ru.job4j.chapter1.question.articles.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.chapter1.question.articles.model.Article;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArticleStore implements Store<Article>, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleStore.class.getSimpleName());

    private final Properties properties;

    private Connection connection;

    public ArticleStore(Properties properties) {
        this.properties = properties;
        initConnection();
        initScheme();
    }

    private void initConnection() {
        LOGGER.info(Constants.InfoMassages.CONNECT_HEAD_MASSAGE);
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty(Constants.PropertyKeys.URL),
                    properties.getProperty(Constants.PropertyKeys.USERNAME),
                    properties.getProperty(Constants.PropertyKeys.PASSWORD)
            );
        } catch (SQLException trouble) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, trouble.getCause());
            throw new IllegalStateException();
        }
    }

    private void initScheme() {
        LOGGER.info(Constants.InfoMassages.INIT_DB_SCHEME_HEAD_MESSAGE);
        try (var statement = connection.createStatement()) {
            var sql = Files.readString(Paths.get(Constants.INIT_DB_SCHEME_REQUEST_PATH));
            statement.execute(sql);
        } catch (Exception e) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
    }

    @Override
    public Article save(Article model) {
        LOGGER.info(Constants.InfoMassages.SAVE_ARTICLE_HEAD_MESSAGE);
        var sql = "insert into articles(text) values(?)";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (model != null) {
                statement.setString(1, model.getText());
                statement.executeUpdate();
                var key = statement.getGeneratedKeys();
                while (key.next()) {
                    model.setId(key.getInt(1));
                }
            }
        } catch (Exception e) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
        return model;
    }

    @Override
    public List<Article> findAll() {
        LOGGER.info(Constants.InfoMassages.FIND_ARTICLES_HEAD_MESSAGE);
        var sql = "select * from articles";
        var articles = new ArrayList<Article>();
        try (var statement = connection.prepareStatement(sql)) {
            var selection = statement.executeQuery();
            while (selection.next()) {
                articles.add(new Article(
                        selection.getInt(Constants.SelectionColumns.ID),
                        selection.getString(Constants.SelectionColumns.TEXT)
                ));
            }
        } catch (Exception e) {
            LOGGER.error(Constants.InfoMassages.ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
        return articles;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private static class Constants {

        static final String INIT_DB_SCHEME_REQUEST_PATH =
                "./src/main/java/ru/job4j/chapter1/question/articles/db/articles.sql";

        static final class PropertyKeys {

            static final String URL = "url";
            static final String USERNAME = "username";
            static final String PASSWORD = "password";
        }

        static final class InfoMassages {

            static final String CONNECT_HEAD_MASSAGE = "Создание подключения к БД статей";
            static final String ERROR_MESSAGE = "Не удалось выполнить операцию: { }";
            static final String INIT_DB_SCHEME_HEAD_MESSAGE = "Инициализация таблицы статей";
            static final String SAVE_ARTICLE_HEAD_MESSAGE = "Сохранение статьи";
            static final String FIND_ARTICLES_HEAD_MESSAGE = "Загрузка всех статей";
        }

        static final class SelectionColumns {

            static final String ID = "id";
            static final String TEXT = "text";
        }
    }
}