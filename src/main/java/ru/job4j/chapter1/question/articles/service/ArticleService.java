package ru.job4j.chapter1.question.articles.service;

import ru.job4j.chapter1.question.articles.model.Article;
import ru.job4j.chapter1.question.articles.model.Word;
import ru.job4j.chapter1.question.articles.store.Store;

public interface ArticleService {

    void generate(Store<Word> wordStore, int count, Store<Article> articleStore);
}
