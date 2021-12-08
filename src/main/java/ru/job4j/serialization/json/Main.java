package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

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

        JSONObject jsonPlatform = new JSONObject("{\"company\":\"Nintendo\", "
                + "\"name\":\"Nintendo switch\", "
                + "\"currentGeneration\":\"true\"}");

        List<String> list = new ArrayList<>();
        list.add("Arcade");
        list.add("Adventure");
        JSONArray jsonArray =  new JSONArray(list);

        final Game nintendoGame = new Game(3, "Super Mario Odyssey",
                new Platform("Nintendo", "Nintendo switch", true),
                new String[] {"Arcade", "Adventure"}, false);

        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("id", nintendoGame.getId());
        jsonObject.put("title", nintendoGame.getTitle());
        jsonObject.put("platform", jsonPlatform);
        jsonObject.put("genres", jsonArray);
        jsonObject.put("multiplayer", nintendoGame.isMultiplayer());

        System.out.println(jsonObject);

        System.out.println(new JSONObject(nintendoGame));
    }
}
