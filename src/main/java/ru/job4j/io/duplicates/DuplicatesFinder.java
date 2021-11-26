package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

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
        for (String duplicate: searcher.getDuplicates()) {
            System.out.printf("Duplicate file - %s" + System.lineSeparator()
                    + "Paths: " + System.lineSeparator(), duplicate);
            for (Path path: searcher.getPaths()) {
                if (path.endsWith(duplicate)) {
                    System.out.println(path.toAbsolutePath());
                }
            }
            System.out.println();
        }
    }
}
