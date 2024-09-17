package persistence;

import model.Deck;
import model.FlashCard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JsonTest {
    protected void checkFlashCard(String front, String back, int points, FlashCard flashcard) {
        assertEquals(front, flashcard.getFront());
        assertEquals(back, flashcard.getBack());
        assertEquals(points, flashcard.getPoints());
    }
}