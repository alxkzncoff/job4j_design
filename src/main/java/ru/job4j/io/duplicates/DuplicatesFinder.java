package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Программа ищет одинаковые файлы в указанной директории и выводит их на экран.
 * @author Aleksandr Kzunetsov.
 * @version 1.0
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DuplicatesVisitor searcher =  new DuplicatesVisitor();
        boolean invalid = true;
        do {
            try {
                System.out.println("Enter folder to scan (for example 'C:\\folder\\' or '.\\') "
                        + "or type 'exit':"
                        + System.lineSeparator());
                String start = scanner.nextLine();
                if (start.equals("exit")) {
                    break;
                }
                Files.walkFileTree(Path.of(start), searcher);
                invalid = false;
            } catch (NoSuchFileException nsf) {
                System.out.println("Folder did not found.");
            } catch (InvalidPathException ip) {
                System.out.println("Path contains wrong characters.");
            }
        } while (invalid);
        searcher.getPaths().entrySet().stream()
                .filter(file -> file.getValue().size() > 1)
                .forEach(file -> file.getValue().
                        forEach(System.out::println));
    }
}
