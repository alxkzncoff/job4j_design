package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Программа для архивации папок. В качестве параметров указывается:
 * -d -directory - папка, которую архивируем;
 * -e -execute - расширение файлов, которое нужно исключить из архивации;
 * -o -output - итоговый архив.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class Zip {

    /**
     * Метод архивирует переданные файлы.
     * @param sources файлы, которые архивируем.
     * @param target куда сохраняем архив.
     */
    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (Path sourcePath: sources) {
                zip.putNextEntry(new ZipEntry(sourcePath.subpath(1, sourcePath.getNameCount())
                        .toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(sourcePath.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Parameters missed. Add three parameters.");
        }
        ArgsName zipArgs = ArgsName.of(args);
        Path start = Paths.get(zipArgs.get("d"));
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    start.toFile().getAbsoluteFile()));
        }
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    start.toFile().getAbsoluteFile()));
        }
        List<Path> files = new ArrayList<>(Search.search(start,
                p -> !p.toFile().getName().endsWith(zipArgs.get("e"))));
        packFiles(files, new File(zipArgs.get("o")));
    }
}
