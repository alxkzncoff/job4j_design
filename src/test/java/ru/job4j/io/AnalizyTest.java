package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.time.temporal.Temporal;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void serverDown() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("500 10:56:01");
            out.println("400 10:57:01");
            out.println("300 10:58:01");
            out.println("400 10:59:01");
            out.println("300 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:56:01;10:58:01;10:59:01;11:01:02;"));
    }
}