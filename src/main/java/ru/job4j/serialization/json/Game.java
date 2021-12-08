package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game {

    @XmlAttribute
    private int id;
    @XmlAttribute
    private String title;
    private Platform platform;

    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private String[] genres;

    @XmlAttribute
    private boolean multiplayer;

    public Game() { }

    public Game(int id, String title, Platform platform, String[] genres, boolean multiplayer) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.genres = genres;
        this.multiplayer = multiplayer;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Platform getPlatform() {
        return platform;
    }

    public String[] getGenres() {
        return genres;
    }

    public boolean isMultiplayer() {
        return multiplayer;
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
