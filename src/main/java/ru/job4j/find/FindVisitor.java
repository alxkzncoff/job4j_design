package ru.job4j.find;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FindVisitor extends SimpleFileVisitor<Path> {

    private final String searchType;
    private final String searchParams;
    private final List<Path> paths = new ArrayList<>();

    public FindVisitor(String searchType, String searchParams) {
        this.searchType = searchType;
        this.searchParams = searchParams;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if ("name".equals(searchType) && file.getFileName().toString().equals(searchParams)) {
            paths.add(file);
        }
        if ("mask".equals(searchType) && file.toFile()
                .getName().endsWith(searchParams.split("\\.")[1])) {
            paths.add(file);
        }
        if (searchType.equals("regex")) {
            Pattern pattern = Pattern.compile(searchParams);
            Matcher matcher = pattern.matcher(file.toFile().getName());
            if (matcher.find()) {
                paths.add(file);
            }
        }
        return super.visitFile(file, attrs);
    }
}
