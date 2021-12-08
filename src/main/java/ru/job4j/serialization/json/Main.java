package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Game game = new Game(1, "Uncharted 4",
                new Platform("Sony", "playstation 4", true),
                new String[] {"FPS", "Adventure"}, true);

        JAXBContext context = JAXBContext.newInstance(Game.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(game, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Game result = (Game) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
