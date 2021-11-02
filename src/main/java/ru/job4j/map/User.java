package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;

    private int children;

    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User u1 = new User("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 13));
        User u2 = new User("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 13));
        Map<User, Object> map = new HashMap<>();
        map.put(u1, new Object());
        map.put(u2, new Object());
        System.out.println(map);
    }
}
