package ru.job4j.io.reader;

import ru.job4j.io.ArgsName;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

/**
 * Класс читает CSV файлы и выводит их. Возможна фильтрация по столбцам.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class CSVReader {

    private static final List<Integer> FILTER = new ArrayList<>();
    private static final List<List<String>> TABLE = new ArrayList<>();
    private static final StringBuilder OUT = new StringBuilder();

    /**
     * Метод обрабатывает входной CSV файл.
     * @param argsName -path - исходный файл;
     *                 -delimiter - разделитель;
     *                 -out - итоговый файл (или вывод на консоль);
     *                 -filter - фильтр по столбцам.
     */
    public static void handle(ArgsName argsName) {
        String delimiter = argsName.get("delimiter");
        String target = argsName.get("out");
        try (Scanner scanner = new Scanner(new BufferedReader(
                new FileReader(argsName.get("path"))))) {
            List<String> row = List.of(scanner.nextLine().split(delimiter));
            TABLE.add(row);
            for (String column: List.of(argsName.get("filter").split(","))) {
                FILTER.add(row.indexOf(column));
            }
            while (scanner.hasNext()) {
                row = List.of(scanner.nextLine().split(delimiter));
                TABLE.add(row);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (List<String> row: TABLE) {
            for (int i = 0; i < row.size(); i++) {
                if (FILTER.contains(i)) {
                    OUT.append(row.get(i)).append(";");
                }
            }
            OUT.deleteCharAt(OUT.length() - 1).append(System.lineSeparator());
        }
        if ("stdout".equals(target)) {
            System.out.println(OUT);
        } else {
            try (PrintWriter pw = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)))) {
                pw.print(OUT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Parameters missed. Add four parameters.");
        }
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}
