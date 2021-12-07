package ru.job4j.serialization.json;

import java.util.Arrays;

public class Game {
    private final int id;
    private final String title;
    private final Platform platform;
    private final String[] genres;
    private final boolean multiplayer;

    public Game(int id, String title, Platform platform, String[] genres, boolean multiplayer) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.genres = genres;
        this.multiplayer = multiplayer;
    }

    @Override
    public String toString() {
        return "Game{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", platform=" + platform
                + ", genres=" + Arrays.toString(genres)
                + ", multiplayer=" + multiplayer
                + '}';
    }
}
