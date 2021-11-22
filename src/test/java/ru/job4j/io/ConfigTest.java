package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test
    public void whenPairWithEmptyLine() {
        String path = "./data/pair_with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPair() {
        String path = "./data/wrong_pair.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }
}