package ui;

import model.Deck;
import model.FlashCard;
import model.Score;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


// Operates the flashcard game
public class FlashCardGameConsole {
    private static final String JSON_STORE = "./data/Deck.json";
    private Scanner input;
    private Deck deck;
    private Score score;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public FlashCardGameConsole() {

        input = new Scanner(System.in);
        deck = new Deck("default deck");
        score = new Score();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        onesTimesTable();
        welcomeScreen();
        runFlashCardGame();
    }

    // MODIFIES: this
    // EFFECTS: adds the 1's times table to deck
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
    // EFFECTS: displays a welcome message
    private void welcomeScreen() {
        System.out.println("\nWelcome to the Math FlashCard Game!");
        System.out.println("\tThe default deck begins with the 1's times tables already included.");
        System.out.println("\tFeel free to add or remove any cards that you please.");
        System.out.println("\tPlease type anything to continue.");
        String command = input.next();
        System.out.println("Heading to main menu...");
    }

    //MODIFIES: this
    //EFFECTS: runs game until user quits
    private void runFlashCardGame() {
        boolean playing = true;
        String command = null;


        while (playing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("8")) {
                playing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nQuitting...");
    }

    // EFFECTS: displays a menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Change deck name");
        System.out.println("\t2 -> Create new flashcard and add to deck");
        System.out.println("\t3 -> Remove flashcard from deck");
        System.out.println("\t4 -> Show cards in deck");
        System.out.println("\t5 -> Study deck");
        System.out.println("\t6- > Save deck to file");
        System.out.println("\t7 -> Load deck from file");
        System.out.println("\t8 -> Quit");
    }

    // EFFECTS: processes command entered by user and navigates to corresponding
    //          screen
    private void processCommand(String command) {
        if (command.equals("1")) {
            newName();
        } else if (command.equals("2")) {
            newCard();
        } else if (command.equals("3")) {
            removeCard();
        } else if (command.equals("4")) {
            showCards();
        } else if (command.equals("5")) {
            doStudy();
        } else if (command.equals("6")) {
            doSave();
        } else if (command.equals("7")) {
            doLoad();
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // MODIFIES: this
    // EFFECTS: changes deck name to string entered by user
    private void newName() {
        String currentName = deck.getName();
        System.out.println("\nCurrent deck name is " + currentName + ".");
        System.out.println("Enter new deck name (no spaces):");
        String name = input.next();
        deck.setName(name);
        System.out.println("\n Deck name has been changed to " + name + ".");
    }

    // MODIFIES: this
    // EFFECTS: creates new flashcard with information entered by user
    //          and adds the card to the deck
    private void newCard() {
        System.out.println("\nEnter equation (eg. 1*1):");
        String front = input.next();

        System.out.println("\nEnter answer (eg. 1):");
        String back = input.next();

        System.out.println("\nEnter how many points the card is worth:");
        int points = input.nextInt();


        FlashCard card = new FlashCard(front,back,points);
        deck.addCard(card);
        String deckName = deck.getName();
        System.out.println("\nNew card " + front + " has been created and added to " + deckName + ".");
    }

    // MODIFIES: this
    // EFFECTS: removes card entered by user from the deck
    private void removeCard() {
        String deckName = deck.getName();

        System.out.println("\nWhich card would you like to remove?");
        String remover = input.next();
        Boolean gotRemoved = deck.removeCard(remover);
        if (gotRemoved) {
            System.out.println(remover + " has been removed from " + deckName + ".");
        } else {
            System.out.println(remover + " could not be found in " + deckName + ".");
        }
    }

    // EFFECTS: shows a list of flashcards in the deck
    private void showCards() {
        String deckName = deck.getName();
        List<FlashCard> cards = deck.getCards();
        int cardNumber = cards.size();
        System.out.println("There are currently " + cardNumber + " cards in " + deckName + ":");
        for (FlashCard card : cards) {
            String cardName = card.getFront();
            System.out.println("    " + cardName);
        }
        System.out.println("\nPlease type anything to continue.");
        String command = input.next();
    }

    // MODIFIES: this
    // EFFECTS: presents the front of a flashcard until user decides to quit
    private void doStudy() {
        boolean studying = true;
        String command = null;

        while (studying) {
            doCard();
            System.out.println("\nWould you like to continue (y/n)?");
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("n")) {
                studying = false;
            }

        }
        System.out.println("Back to the main menu...");

    }

    // MODIFIES: this
    // EFFECTS: gets random card and asks user to answer;
    //          if correct, adds point to score.
    //          if incorrect, subtracts points from score.
    private void doCard() {
        FlashCard card = getRandomCard();
        String question = card.getFront();
        String correctAnswer = card.getBack();
        int points = card.getPoints();


        System.out.println("What is " + question + "?");
        String userAnswer = input.next();
        if (userAnswer == correctAnswer) {
            score.addToScore(points);
            System.out.println("Correct.");
            System.out.println(points + " points have been added to your score.");

        } else {
            score.subtractFromScore(points);
            System.out.println("Incorrect.");
            System.out.println(points + " points have been subtracted from your score.");

        }
        int currentScore = score.getScore();
        System.out.println("Your total score is now: " + currentScore + ".");

    }

    // EFFECTS: gets random card from deck
    private FlashCard getRandomCard() {
        List<FlashCard> cards = deck.getCards();
        Random rand = new Random();
        int index = rand.nextInt(cards.size());
        FlashCard card = cards.get(index);
        return card;
    }

    // EFFECTS: saves the Deck to file
    public void doSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(deck);
            jsonWriter.close();
            System.out.println("Saved " + deck.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads deck from file
    public void doLoad() {
        try {
            deck = jsonReader.read();
            System.out.println("Loaded " + deck.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}





