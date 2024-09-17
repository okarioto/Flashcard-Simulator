package ui;


import model.Deck;
import model.Event;
import model.EventLog;
import model.FlashCard;
import model.Score;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

// Operates Flashcard game using GUI
public class FlashCardGameGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/Deck.json";
    private static final int width = 500;
    private static final int height = 500;
    private Deck deck;
    private Score score;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton changeDeckNameButton;
    private JButton newCardButton;
    private JButton showCardsButton;
    private JButton removeCardButton;
    private JButton studyDeckButton;
    private JButton saveButton;
    private JButton loadButton;
    private ImageIcon picture;

    public FlashCardGameGUI() {
        deck = new Deck("Default Deck");
        score = new Score();
        picture = new ImageIcon("./data/tobs.jpg");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Default Deck");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width,height);
        onesTimesTable();
        setLayout(new GridLayout(7,1));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog el = EventLog.getInstance();
                for (Event event : el) {
                    System.out.println("\n" + event.toString());
                }
            }
        });

        buttonAdder();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds the 1's times table to deck
    private void onesTimesTable() {
        FlashCard f1 = new FlashCard("1*1","1",1);
        FlashCard f2 = new FlashCard("1*2","2",1);
        FlashCard f3 = new FlashCard("1*3","3",1);
        FlashCard f4 = new FlashCard("1*4","4",1);
        FlashCard f5 = new FlashCard("1*5","5",1);
        FlashCard f6 = new FlashCard("1*6","6",1);
        FlashCard f7 = new FlashCard("1*7","7",1);
        FlashCard f8 = new FlashCard("1*8","8",1);
        FlashCard f9 = new FlashCard("1*9","9",1);
        FlashCard f10 = new FlashCard("1*10","10",1);

        deck.addCard(f1);
        deck.addCard(f2);
        deck.addCard(f3);
        deck.addCard(f4);
        deck.addCard(f5);
        deck.addCard(f6);
        deck.addCard(f7);
        deck.addCard(f8);
        deck.addCard(f9);
        deck.addCard(f10);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds buttons to GUI
    private void buttonAdder() {
        changeDeckNameButton = new JButton("Change Deck Name");
        newCardButton = new JButton("Make New Card");
        showCardsButton = new JButton("Show cards");
        removeCardButton = new JButton("Remove Card");
        studyDeckButton = new JButton("Study Deck");
        saveButton = new JButton("Save Deck");
        loadButton = new JButton("Load Deck");

        changeDeckNameButton.addActionListener(this);
        newCardButton.addActionListener(this);
        showCardsButton.addActionListener(this);
        removeCardButton.addActionListener(this);
        studyDeckButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        add(changeDeckNameButton);
        add(newCardButton);
        add(removeCardButton);
        add(showCardsButton);
        add(studyDeckButton);
        add(saveButton);
        add(loadButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeDeckNameButton) {
            nameChanger();
        }
        if (e.getSource() == newCardButton) {
            cardAdder();
        }
        if (e.getSource() == removeCardButton) {
            cardRemover();
        }
        if (e.getSource() == showCardsButton) {
            cardsShower();
        }
        if (e.getSource() == studyDeckButton) {
            deckStudier();
        }
        if (e.getSource() == saveButton) {
            deckSaver();
        }
        if (e.getSource() == loadButton) {
            deckLoader();
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the deck name
    private void nameChanger() {
        String newName = JOptionPane.showInputDialog("New Name:");
        deck.setName(newName);
        setTitle(newName);
    }

    // MODIFIES: this
    // EFFECTS: adds card entered by user to deck
    private void cardAdder() {
        String front = JOptionPane.showInputDialog("Front of card (eg.1+1, 2*4):");
        String back = JOptionPane.showInputDialog("Back of card (eg. 3, 3.2):");
        String points = JOptionPane.showInputDialog("Points of card (eg. 1, 6, 7):");
//        Double backDouble = Double.parseDouble(back);
        int pointsInt = Integer.parseInt(points);
        try {
            FlashCard newCard = new FlashCard(front,back,pointsInt);
            deck.addCard(newCard);
            JOptionPane.showMessageDialog(null,
                    "Flashcard " + newCard.getFront() + " has been added to " + deck.getName());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid values were entered. Card failed to add");
        }


    }


    // MODIFIES: this
    // EFFECTS: removes card entered by user
    private void cardRemover() {
        String cardName = JOptionPane.showInputDialog("Card Name:");
        Boolean gotRemoved = deck.removeCard(cardName);
        if (gotRemoved) {
            JOptionPane.showMessageDialog(null,
                    "Flashcard " + cardName + " has been removed from " + deck.getName());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Flashcard " + cardName + " could not be found in " + deck.getName());
        }

    }


    // EFFECTS: shows all cards in deck
    private void cardsShower() {
        List<FlashCard> cardsList = deck.getCards();
        String cards = "The cards in " + deck.getName() + " are: ";
        for (FlashCard card : cardsList) {
            String cardName = card.getFront();
            cards = cards + "\n " + "\t" + cardName;
        }
        JOptionPane.showMessageDialog(null,cards);
    }

    // EFFECTS: displays Flashcards randomly, and
    //          if correct answer is given, add points,
    //          if not, subtract points.
    private void deckStudier() {
        JPanel panel = startStudyingPanelMaker();
        JOptionPane.showMessageDialog(null,panel,"Start",JOptionPane.PLAIN_MESSAGE);
        while (true) {
            FlashCard card = getRandomCard();
            String front = card.getFront();
            String back = card.getBack();
            int points = card.getPoints();
            String userAnswer = JOptionPane.showInputDialog(front + " (" + points + " points)");
            if (userAnswer.equals(back)) {
                score.addToScore(points);
                JOptionPane.showMessageDialog(null,
                        "Correct! " + points + " points has been added. "
                                + "\n You have " + score.getScore() + " points now");
            } else {
                score.subtractFromScore(points);
                JOptionPane.showMessageDialog(null,
                        "Incorrect! " + points + " points has been removed."
                                + "\n You have " + score.getScore() + " points now");
            }

        }
    }

    // EFFECTS: creates panel with image
    private JPanel startStudyingPanelMaker() {
        JLabel startMessage = new JLabel("Start Studying!");
        JLabel image = new JLabel(picture);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(startMessage,BorderLayout.NORTH);
        panel.add(image,BorderLayout.CENTER);
        panel.setVisible(true);
        return panel;
    }

    // EFFECTS: returns a random card from the deck
    private FlashCard getRandomCard() {
        List<FlashCard> cards = deck.getCards();
        Random rand = new Random();
        int index = rand.nextInt(cards.size());
        FlashCard card = cards.get(index);
        return card;
    }

    // EFFECTS: saves the Deck to file
    private void deckSaver() {
        try {
            jsonWriter.open();
            jsonWriter.write(deck);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved" + deck.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads deck from file
    private void deckLoader() {
        try {
            deck = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded " + deck.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE);
        }
    }


}
