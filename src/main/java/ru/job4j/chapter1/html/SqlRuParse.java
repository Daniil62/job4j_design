package ru.job4j.chapter1.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SqlRuParse {

    private static final String PATH = "https://www.sql.ru/forum/job-offers/";
    private static final String CSS_QUERY = ".postslisttopic";
    private static final String ATTRIBUTE_KEY = "href";
    private static final int FIRST_DOC_ELEMENT = 0;
    private static final int CHILDREN_NUMBER = 5;

    public void parsePages(int count) throws IOException {
        for (int i = 1; i <= count; i++) {
            System.out.println(String.format(
                    "%s page %d %s", System.lineSeparator(), i, System.lineSeparator()));
            Document doc = Jsoup.connect(String.format("%s%d", PATH, i)).get();
            Elements row = doc.select(CSS_QUERY);
            for (Element td : row) {
                Element href = td.child(FIRST_DOC_ELEMENT);
                System.out.println(href.attr(ATTRIBUTE_KEY));
                System.out.println(href.text());
                System.out.println(td.parent().children().get(CHILDREN_NUMBER).text());
            }
        }
    }

    public static void main(String[] args) throws Exception {

        new SqlRuParse().parsePages(5);
    }
}
