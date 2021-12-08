package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "platform")
public class Platform {

    @XmlAttribute
    private String company;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean currentGeneration;

    public Platform() { }

    public Platform(String company, String name, boolean currentGeneration) {
        this.company = company;
        this.name = name;
        this.currentGeneration = currentGeneration;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public boolean isCurrentGeneration() {
        return currentGeneration;
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
