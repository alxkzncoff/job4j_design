package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Класс анализирует статус сервера и выводит период времени,
 * когда сервер был не доступен.
 * @author Aleksandr Kuzntesov.
 * @version 1.0
 */
public class Analizy {
    private static boolean serverUp = true;

    public static void unavailable(String source, String target) {
        StringBuilder period = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(line -> {
                if ((line.startsWith("4") || line.startsWith("5")) && serverUp) {
                    period.append(line.split(" ")[1]).append(";");
                    serverUp = false;
                }
                if ((line.startsWith("2") || line.startsWith("3")) && !serverUp) {
                    period.append(line.split(" ")[1]).append(";").append(System.lineSeparator());
                    serverUp = true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            out.println(period);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "./data/server.log";
        String target = "./data/unavailable.csv";
        Analizy.unavailable(source, target);
    }
}
