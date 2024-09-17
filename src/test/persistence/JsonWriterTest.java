package persistence;

import model.Deck;
import model.FlashCard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Deck deck = new Deck("My Deck");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDeck() {
        try {
            Deck deck = new Deck("My Deck");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDeck.json");
            writer.open();
            writer.write(deck);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDeck.json");
            deck = reader.read();
            assertEquals("My Deck", deck.getName());
            List<FlashCard> cards = deck.getCards();
            assertEquals(0, cards.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDeck() {
        try {
            Deck deck = new Deck("My Deck");
            deck.addCard(new FlashCard("1","1",1));
            deck.addCard(new FlashCard("2","2",2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDeck.json");
            writer.open();
            writer.write(deck);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDeck.json");
            deck = reader.read();
            assertEquals("My Deck", deck.getName());
            List<FlashCard> cards = deck.getCards();
            assertEquals(2, cards.size());
            checkFlashCard("1","1",1,cards.get(0));
            checkFlashCard("2","2",2,cards.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}