package persistence;

import model.Deck;
import model.FlashCard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Deck deck = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDeck() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDeck.json");
        try {
            Deck deck = reader.read();
            assertEquals("My Deck", deck.getName());
            List<FlashCard> cards = deck.getCards();
            assertEquals(0, cards.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDeck() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDeck.json");
        try {
            Deck deck = reader.read();
            assertEquals("My Deck", deck.getName());
            List<FlashCard> cards = deck.getCards();
            assertEquals(2, cards.size());
            checkFlashCard("1","1",1,cards.get(0));
            checkFlashCard("2","2",2,cards.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}