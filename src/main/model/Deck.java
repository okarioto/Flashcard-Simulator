package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

// Represents a collection of flashcards
public class Deck implements Writable {
    private String name;
    private List<FlashCard> cards;


    // REQUIRES: non-zero length String
    // EFFECTS: creates deck with given name and no cards
    public Deck(String name) {
        this.name = name;
        this.cards = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given flashcard to deck
    public void addCard(FlashCard card) {
        cards.add(card);
        EventLog.getInstance().logEvent(new Event(card.getFront() + " added to deck."));
    }

    // REQUIRES: non-zero length deck
    // MODIFIES: this
    // EFFECTS: removes flashcard from deck and returns true. Returns false if card cannot be found.
    //          If multiple flashcards with the same name, removes the first one added.
    public boolean removeCard(String remover) {
        for (int i = 0; i < cards.size(); i++) {
            FlashCard card = cards.get(i);
            String name = card.getFront();
            if (name.equals(remover)) {
                cards.remove(i);
                EventLog.getInstance().logEvent(new Event(card.getFront() + " removed from deck."));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event("Card failed to remove from deck."));
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cards", cardsToJson());
        return json;
    }

    // EFFECTS: returns cards in this deck as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FlashCard c : cards) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    public String getName() {
        return name;
    }

    public List<FlashCard> getCards() {
        return cards;
    }

    public void setName(String name) {
        this.name = name;
    }
}
