package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    private Score s1;

    @BeforeEach
    void runBefore() {
        s1 = new Score();
    }

    @Test
    void testConstructor() {
        assertEquals(0,s1.getScore());
    }

    @Test
    void testAddToScore() {
        assertEquals(0,s1.getScore());
        s1.addToScore(1);
        assertEquals(1,s1.getScore());
    }

    @Test
    void testSubtractFromScore() {
        assertEquals(0,s1.getScore());
        s1.subtractFromScore(1);
        assertEquals(-1,s1.getScore());
    }

    @Test
    void testResetScore() {
        assertEquals(0,s1.getScore());
        s1.addToScore(5);
        assertEquals(5,s1.getScore());
        s1.resetScore();
        assertEquals(0,s1.getScore());
    }

    @Test
    void testToJson() {
        s1.addToScore(1);
        JSONObject json = s1.toJson();
        int jsonScore1 = json.getInt("score");
        System.out.println(jsonScore1);
        assertEquals(1,jsonScore1);
    }

}

