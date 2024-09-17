package persistence;


import model.Deck;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.FlashCard;
import org.json.*;

// Represents a reader that reads Deck from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Deck from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Deck read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDeck(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses deck from JSON object and returns it
    private Deck parseDeck(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Deck deck = new Deck(name);
        addCards(deck, jsonObject);
        return deck;
    }

    // MODIFIES: deck
    // EFFECTS: parses flashcards from JSON object and adds them to Deck
    private void addCards(Deck deck, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cards");
        for (Object json : jsonArray) {
            JSONObject nextFlashCard = (JSONObject) json;
            addFlashCard(deck, nextFlashCard);
        }
    }

    // MODIFIES: deck
    // EFFECTS: parses flashcards from JSON object and adds it to Deck
    private void addFlashCard(Deck deck, JSONObject jsonObject) {
        String front = jsonObject.getString("front");
        String back = jsonObject.getString("back");
        int points = jsonObject.getInt("points");
        FlashCard flashCard = new FlashCard(front, back, points);
        deck.addCard(flashCard);
    }
}