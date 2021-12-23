package ru.job4j.io;

import java.util.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Класс читает файлы конфигурации.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод считывает все ключи в карту values.
     */
    public void load() {
        List<String> out = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.stream()
                .filter(line -> !line.startsWith("#") && line.length() > 0)
                .forEach(line -> {
                    if (line.split("=").length < 2 || line.split("=")[0].length() == 0) {
                        throw new IllegalArgumentException("pair error");
                    } else {
                        values.put(line.split("=")[0], line.split("=")[1]);
                    }
                });
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}