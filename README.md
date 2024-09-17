# Simple Quiz Game
## A flashcard like game to help learn 
For my personal project, I would like to design a game where the user is 
presented with a card and a simple problem on it. They will then be prompted
to enter the answer to the question in an answer box. I would like to make it so 
the game is able to have some sort of scoring system . The game should also be pause-able
and savable at any given point allowing the current cards in the deck to be kept. I would also like
to have the user be able to add their own flashcards with their own answers into
the game. The application will be used by younger students who are just starting arithmetics 
to practice things such as: 
- Addition 
- Subtraction 
- Simple multiplication 
- Simple division 

This project is of interest to me because as a child, my mother would
make me practice  simple math problems using a 10 by 10 grid filled with numbers 
where I would just continuously solve. This was quite boring to me as a child,
and I was hoping to design something slightly more interactive to make the practicing 
of math a little more interesting than starting at a grid filled with numbers. 

## User story
- As a user, I wish to ba able to make and add a new flashcard
- As a user, I wish to be able to see a list of all the cards in a deck
- As a user, I wish to be able to remove a flashcard from a deck
- As a user, I wish to be able to study the deck
- As a user, I wish to be able to save the deck that I have adjusted
- As a user, I wish to be able to load the saved deck if I wish to do so

# Instructions
- You can generate the first required action related to adding Xs to a Y by clicking the Make New Card Button.
- You can generate the second required action related to adding Xs to a Y by clicking the Show Cards Button.
- You can locate my visual component by clicking the Study Deck Button.
- You can save the state of my application by clicking the Save Deck Button.
- You can reload the state of my application by clicking the Load Deck Button.

# Phase 4: Task 2
Wed Apr 12 14:45:18 PDT 2023
1*1 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*2 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*3 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*4 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*5 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*6 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*7 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*8 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*9 added to deck.

Wed Apr 12 14:45:18 PDT 2023
1*10 added to deck.

Wed Apr 12 14:45:23 PDT 2023
1*1 removed from deck.

# Phase 4: Task 3
To improve the design of this project, I would refactor the FlashCardGameGUI class since I feel it has low cohesion.
The class has a method where the entire one's times table is added to the deck every time the game is run. I feel that
method should have been placed within the Deck or FlashCard Class as a method that creates the one's times table and 
just be called upon within the FlashCardGameGUI class rather than running the method within FlashCardGameGUI.
