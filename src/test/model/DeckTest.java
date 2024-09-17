package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class for class Deck
class DeckTest {
   private Deck d1;
   private FlashCard f1;
   private FlashCard f2;
   private FlashCard f3;
   private List<FlashCard> cards;

    @BeforeEach
    void runBefore() {
       d1 = new Deck("d1");
       f1 = new FlashCard("1+1=","2",1);
       f2 = new FlashCard("1-1=","0",2);
       f3 = new FlashCard("1*1=","2",3);
       cards = d1.getCards();
   }

   @Test
    void testConstructor() {
       assertEquals("d1",d1.getName());
       assertEquals(0,cards.size());
   }

   @Test
    void testAddCard() {
       assertEquals(0,cards.size());
       d1.addCard(f1);
       assertEquals(1,cards.size());
       assertEquals(f1,cards.get(0));

       d1.addCard(f2);
       assertEquals(2,cards.size());
       assertEquals(f2,cards.get(1));
   }

   @Test
    void testRemoveCardFromFirst() {
       assertEquals(0,cards.size());
       d1.addCard(f1);
       d1.addCard(f2);
       d1.addCard(f3);
       assertEquals(3,cards.size());


       assertTrue(d1.removeCard("1+1="));
       assertFalse(d1.removeCard("1+1="));
       assertEquals(2,cards.size());
       assertEquals(f2,cards.get(0));
       assertEquals(f3,cards.get(1));
   }

    @Test
    void testRemoveCardFromMiddle() {
        assertEquals(0,cards.size());
        d1.addCard(f1);
        d1.addCard(f2);
        d1.addCard(f3);
        assertEquals(3,cards.size());

        d1.removeCard("1-1=");
        assertEquals(2,cards.size());
        assertEquals(f1,cards.get(0));
        assertEquals(f3,cards.get(1));
    }

    @Test
    void testRemoveCardFromLast() {
        assertEquals(0,cards.size());
        d1.addCard(f1);
        d1.addCard(f2);
        d1.addCard(f3);
        assertEquals(3,cards.size());

        d1.removeCard("1*1=");
        assertEquals(2,cards.size());
        assertEquals(f1,cards.get(0));
        assertEquals(f2,cards.get(1));
    }

    @Test
    void testEmptyDeckToJson() {
        JSONObject json = d1.toJson();
        String jsonName = json.getString("name");
        JSONArray jsonCards = json.getJSONArray("cards");
        assertEquals("d1",jsonName);
        assertEquals(0,jsonCards.length());
    }

    @Test
    void testGeneralDeckToJson() {
        d1.addCard(f1);
        d1.addCard(f2);
        JSONObject json = d1.toJson();
        String jsonName = json.getString("name");
        JSONArray jsonCards = json.getJSONArray("cards");
        assertEquals("d1",jsonName);
        assertEquals(2,jsonCards.length());
    }


    @Test
    void testSetName() {
        assertEquals("d1",d1.getName());
        d1.setName("d2");
        assertEquals("d2",d1.getName());
    }
}