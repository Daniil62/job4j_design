package ru.job4j.chapter1.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("###"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairHasOnlyComments() {
        String path = "./data/comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.size(), is(0));
    }

    @Test
    public void whenPairHasCommentAndEmptyLines() {
        String path = "./data/with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.size(), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairInvalidate() {
        String path = "./data/invalidate.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairIsEmpty() {
        String path = "./data/empty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.size(), is(0));
    }
}
