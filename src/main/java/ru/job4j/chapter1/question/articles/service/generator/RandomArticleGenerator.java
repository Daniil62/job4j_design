package ru.job4j.chapter1.question.articles.service.generator;

import ru.job4j.chapter1.question.articles.model.Article;
import ru.job4j.chapter1.question.articles.model.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomArticleGenerator implements ArticleGenerator {

    private static final String DELIMITER = " ";

    @Override
    public Article generate(List<Word> words) {
        var wordsCopy = new ArrayList<>(words);
        Collections.shuffle(wordsCopy);
        var content = wordsCopy.stream()
                .map(Word::getValue)
                .collect(Collectors.joining(DELIMITER));
        return new Article(content);
    }
}
