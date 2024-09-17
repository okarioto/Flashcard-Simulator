package model;

import org.json.JSONObject;
import persistence.Writable;

//Keeps track of the score the user has
public class Score implements Writable {
    private int score;

    public Score() {
        score = 0;
    }

    // MODIFIES: this
    // EFFECTS: resets score to 0
    public void resetScore() {
        score = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds given integer to score
    public void addToScore(int num) {
        score += num;
    }

    // MODIFIES: this
    // EFFECTS: subtract given integer from score
    public void subtractFromScore(int num) {
        score -= num;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("score", score);
        return json;
    }

    public int getScore() {
        return score;
    }
}