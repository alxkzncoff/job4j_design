package ru.job4j.serialization.json;

public class Platform {
    private final String company;
    private final String name;
    private final boolean currentGeneration;

    public Platform(String company, String name, boolean currentGeneration) {
        this.company = company;
        this.name = name;
        this.currentGeneration = currentGeneration;
    }

    @Override
    public String toString() {
        return "Platform{"
                + "company='" + company + '\''
                + ", name='" + name + '\''
                + ", currentGeneration=" + currentGeneration
                + '}';
    }
}
