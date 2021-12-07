package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(1, "Uncharted 4",
                new Platform("Sony", "playstation 4", true),
                new String[] {"FPS", "Adventure"}, true);

        final Gson gson = new GsonBuilder().create();
        String gameJson = gson.toJson(game);
        System.out.println(gameJson);

        final Game jsonGame = gson.fromJson(gameJson, Game.class);
        System.out.println(jsonGame);
    }
}
