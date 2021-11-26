package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Set<FileProperty> files = new HashSet<>();

    private final Set<String> duplicates = new HashSet<>();

    private final Set<Path> paths = new HashSet<>();

    public Set<String> getDuplicates() {
        return duplicates;
    }

    public Set<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        paths.add(file.toAbsolutePath());
        if (!files.add(new FileProperty(file.toFile().length(), file.toFile().getName()))) {
            duplicates.add(file.toFile().getName());
        }
        return super.visitFile(file, attrs);
    }
}
