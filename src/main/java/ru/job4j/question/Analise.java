package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс определяет разницу между начальным и измененным состоянием множества.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class Analise {

    /**
     * Метод возвращает информацию об изменении состояния множества.
     * @param previous предыдущее множество.
     * @param current текущее множество.
     * @return Info.
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        if (previous.equals(current)) {
            return info;
        }
        Map<Integer, String> currentMap = current.stream().collect(
                Collectors.toMap(User::getId, User::getName));
        for (User user: previous) {
            if (currentMap.containsKey(user.getId())) {
                if (!currentMap.get(user.getId()).equals(user.getName())) {
                    info.setChanged(info.getChanged() + 1);
                }
            }
            if (!currentMap.containsKey(user.getId())) {
                info.setDeleted(info.getDeleted() + 1);
            }
            currentMap.remove(user.getId());
            info.setAdded(currentMap.size());
        }
        return info;
    }
}
