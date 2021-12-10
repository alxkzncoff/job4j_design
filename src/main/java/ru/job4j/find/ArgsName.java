package ru.job4j.find;

import java.util.HashMap;
import java.util.Map;

/**
 * Программа принимает массив параметров
 * и разбивает их на пары: ключ, значение.
 * @author Alelsandr Kuznetsov.
 * @version 1.0
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Parameters missed. Add four parameters.");
        }
        for (String arg : args) {
            if (!arg.startsWith("-") || !arg.contains("=")) {
                throw new IllegalArgumentException("Wrong parameters. "
                        + "Use pattern -key=value.");
            }
            String[] parameter = arg.substring(1).split("=");
            if (parameter.length < 2) {
                throw new IllegalArgumentException("Wrong parameters. "
                        + "Key or value missed.");
            }
            values.put(parameter[0], parameter[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
