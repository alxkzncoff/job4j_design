package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> paths = new HashMap<>();

    public Map<FileProperty, List<Path>> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().length(),
                file.toFile().getName());
        if (paths.containsKey(currentFile)) {
            paths.get(currentFile).add(file);
        } else {
            List<Path> pathsList = new ArrayList<>();
            pathsList.add(file);
            paths.put(currentFile, pathsList);
        }
        return super.visitFile(file, attrs);
    }
}
