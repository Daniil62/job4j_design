package ru.job4j.chapter1.question.articles.service.generator;

import ru.job4j.chapter1.question.articles.model.Article;
import ru.job4j.chapter1.question.articles.model.Word;

import java.util.List;

public interface ArticleGenerator {

    Article generate(List<Word> words);
}
