package ru.job4j.io.chat;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Random;
import java.nio.charset.*;

/**
 * Консольный чат с ботом. Для начала общения пользователь должен ввести слово-фразу.
 * Бот случайным образом выводит в ответ одну из фраз текстового файла.
 * Пользователь может ввести слово "стоп", чтобы бот больше не выводил фразы,
 * при этом пользователь может продолжать вводить свои слова-фразы.
 * Для активации бота пользователь должен ввести фразу "продолжить".
 * При вводе слова "закончить" программа прекращает работу.
 * Все слова-фразы пользователя и бота записываются в лог файл.
 * @author Aleksandr Kuznetsov.
 * @version 1.1
 */
public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private String status;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод содержит логику чата.
     */
    public void run() {
        List<String> bot = readPhrases();
        List<String> log = new ArrayList<>();
        Random answerIndex = new Random();
        System.out.printf("Добро пожаловать в чат. "
                + "Для начала общения просто введите слово или фразу."
                + System.lineSeparator() + "Список команд:"
                + System.lineSeparator() + " -%s - остановить бота;"
                + System.lineSeparator() + " -%s - активировать бота;"
                + System.lineSeparator() + " -%s - завершить работу чата."
                + System.lineSeparator(), STOP, CONTINUE, OUT);
        while (!OUT.equals(status)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пользователь: ");
            String user = scanner.nextLine();
            command(user);
            log.add("Пользователь: " + System.lineSeparator() + user + System.lineSeparator());
            if (!OUT.equals(status) && !STOP.equals(status)) {
                String answer = bot.get(answerIndex.nextInt(bot.size()));
                System.out.println("Бот: " + System.lineSeparator() + answer);
                log.add("Бот: " + System.lineSeparator() + answer + System.lineSeparator());
            }
        }
        saveLog(log);
    }

    /**
     * Метод читает фразы из файла.
     * @return Список строк с фразами.
     */
    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            rsl = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Метод сохраняет лог чата в файл.
     * @param log - лог чата.
     */
    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            for (String line: log) {
                pw.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод определяет состояние чата на основе данных,
     * вводимых пользователем.
     * @param str сообщение пользователя.
     */
    private void command(String str) {
        if (STOP.equals(str)) {
            status = STOP;
        }
        if (CONTINUE.equals(str)) {
            status = CONTINUE;
        }
        if (OUT.equals(str)) {
            status = OUT;
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/main/java/ru/job4j/io/chat/log.txt",
                "src/main/java/ru/job4j/io/chat/bot.txt");
        cc.run();
    }
}
