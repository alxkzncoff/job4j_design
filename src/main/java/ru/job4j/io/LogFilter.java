package ru.job4j.io;

import java.util.List;
import java.io.*;
import java.util.stream.Collectors;

/**
 * Реализация простого фильтра логов.
 * @author Aleksandr Kuzntsov.
 * @version 1.0
 */
public class LogFilter {
    /**
     * Метод читает данные из файла.
     * @param file файл откуда берутся данные.
     * @return результат фильтрации.
     */
    public static List<String> filter(String file) {
        List<String> rsl = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = in.lines().filter(l -> l.contains(" 404 ")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Метод сохраняет результат работы метода filter в отдельный файл.
     * @param log отфильтрованные данные.
     * @param file куда сохраняем данные.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String line: log) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String report: log) {
            System.out.println(report);
        }
        save(log, "404.txt");
    }
}
