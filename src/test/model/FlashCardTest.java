package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashCardTest {
    private FlashCard f1;

    @BeforeEach
    void runBefore() {
        f1 = new FlashCard("1+1=","2",1);
    }

    @Test
    void testConstructor() {
        assertEquals("1+1=",f1.getFront());
        assertEquals("2",f1.getBack());
        assertEquals(1,f1.getPoints());
    }

    @Test
    void testToJson() {
        JSONObject json = f1.toJson();
        String jsonFront = json.getString("front");
        double jsonBack = json.getDouble("back");
        int jsonPoints = json.getInt("points");
        assertEquals("1+1=",jsonFront);
        assertEquals(2,jsonBack);
        assertEquals(1,jsonPoints);
    }
    @Test
    void testSetters() {
        f1.setFront("1-1=");
        f1.setBack("0");
        f1.setPoints(2);

        assertEquals("1-1=",f1.getFront());
        assertEquals("0",f1.getBack());
        assertEquals(2,f1.getPoints());
    }
}
