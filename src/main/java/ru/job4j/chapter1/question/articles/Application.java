package ru.job4j.chapter1.question.articles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.chapter1.question.articles.service.SimpleArticleService;
import ru.job4j.chapter1.question.articles.service.generator.RandomArticleGenerator;
import ru.job4j.chapter1.question.articles.store.ArticleStore;
import ru.job4j.chapter1.question.articles.store.WordStore;

import java.io.InputStream;
import java.util.Properties;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class.getSimpleName());

    public static final int TARGET_COUNT = 1_000_000;

    private static final String HEAD_MASSAGE = "Загрузка настроек приложения";
    private static final String PROPERTIES_FILE_NAME = "app.properties";
    private static final String ERROR_MESSAGE = "Не удалось загрузить настройки. { }";

    private static Properties loadProperties() {
        LOGGER.info(HEAD_MASSAGE);
        var properties = new Properties();
        try (InputStream in = Application.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            if (in != null) {
                properties.load(in);
            }
        } catch (Exception e) {
            LOGGER.error(ERROR_MESSAGE, e.getCause());
            throw new IllegalStateException();
        }
        return properties;
    }

    public static void main(String[] args) {
        var properties = loadProperties();
        var articleService = new SimpleArticleService(new RandomArticleGenerator());
        articleService.generate(new WordStore(properties), TARGET_COUNT, new ArticleStore(properties));
    }
}
