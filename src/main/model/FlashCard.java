package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a flashcard with information on the front and back and the difficulty of the card
public class FlashCard implements Writable {
    private String front; // information on the front of the card
    private String back;  // information on the back of the card
    private int points;    // difficulty of the card

    // REQUIRES: non-zero length string
    // EFFECTS: front of card is set to front; back of card is set to back,
    //          points of card is set to points
    public FlashCard(String front,String back,int points) {
        this.front = front;
        this.back = back;
        this.points = points;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("front", front);
        json.put("back", back);
        json.put("points", points);
        return json;
    }


    // EFFECTS: sets the amount of points to the given integer
    public void setPoints(int points) {
        this.points = points;
    }

    // EFFECTS: sets the front of the card to given string
    public void setFront(String front) {
        this.front = front;
    }

    // EFFECTS: sets the back of the card to given double
    public void setBack(String back) {
        this.back = back;
    }

    public String getFront() {
        return front;
    }

    public int getPoints() {
        return points;
    }

    public String getBack() {
        return back;
    }
}
