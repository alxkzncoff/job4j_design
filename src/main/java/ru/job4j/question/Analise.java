package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

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
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Info info = new Info(added, changed, deleted);
        if (previous.equals(current)) {
            return info;
        }
        Map<Integer, String> currentMap = current.stream().collect(
                Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> previousMap = previous.stream().collect(
                Collectors.toMap(User::getId, User::getName));
        Iterator<User> currentIt = current.iterator();
        Iterator<User> previousIt = previous.iterator();
        while (currentIt.hasNext() || previousIt.hasNext()) {
            if (currentIt.hasNext()) {
                User currentNext = currentIt.next();
                if (!previousMap.containsKey(currentNext.getId())) {
                    added++;
                    info.setAdded(added);
                }

            }
            if (previousIt.hasNext()) {
                User previousNext = previousIt.next();
                if (!currentMap.containsKey(previousNext.getId())) {
                    deleted++;
                    info.setDeleted(deleted);
                }
                if (currentMap.get(previousNext.getId()) != null
                        && !Objects.equals(
                                currentMap.get(previousNext.getId()), previousNext.getName())) {
                    changed++;
                    info.setChanged(changed);
                }
            }
        }
        return info;
    }
}
