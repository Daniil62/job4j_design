package ru.job4j.chapter1.question.articles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.chapter1.question.articles.model.Article;
import ru.job4j.chapter1.question.articles.model.Word;
import ru.job4j.chapter1.question.articles.service.generator.ArticleGenerator;
import ru.job4j.chapter1.question.articles.store.Store;

import java.util.stream.IntStream;

public class SimpleArticleService implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleArticleService.class.getSimpleName());

    private static final String HEAD_INFO_MESSAGE = "Геренация статей в количестве {}";
    private static final String RESULT_INFO_MESSAGE = "Сгенерирована статья № {}";

    private final ArticleGenerator articleGenerator;

    public SimpleArticleService(ArticleGenerator articleGenerator) {
        this.articleGenerator = articleGenerator;
    }

    @Override
    public void generate(Store<Word> wordStore, int count, Store<Article> articleStore) {
        LOGGER.info(HEAD_INFO_MESSAGE, count);
        var words = wordStore.findAll();
        IntStream.iterate(0, i -> i < count, i -> i + 1)
                .peek(i -> LOGGER.info(RESULT_INFO_MESSAGE, i))
                .mapToObj((x) -> articleGenerator.generate(words))
                .forEach(articleStore::save);
    }
}