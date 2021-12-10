package ru.job4j.find;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * Программа ищет данные в заданном каталоге и подкаталогах.
 * Можно настраивать параметры поиска.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class Finder {

    /**
     * Метод ищет файлы в указанной директории по заданным параметрам.
     * @param args - -d - директория, в которой начинать поиск;
     *               -n - имя файла, маска, либо регулярное выражение;
     *               -t - тип поиска:
     *                          1. mask - искать по маске;
     *                          2. name - искать по имени;
     *                          3. regex - по регулярному выражению.
     * @throws IOException
     */
    public static void find(ArgsName args) throws IOException {
        FindVisitor searcher = new FindVisitor(args.get("t"), args.get("n"));
        Path start = Path.of(args.get("d"));
        Files.walkFileTree(start, searcher);
        try (PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(args.get("o"))))) {
            for (Path path: searcher.getPaths()) {
                pw.print(path + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName finderArgs = ArgsName.of(args);
        try {
            Finder.find(finderArgs);
        } catch (NoSuchFileException nsf) {
            System.out.println("Folder did not found.");
        } catch (InvalidPathException ip) {
            System.out.println("Path contains wrong characters.");
        }
    }
}
