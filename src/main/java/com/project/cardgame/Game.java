package com.project.cardgame;

//Singleton class to represent the game
public class Game {

    private static Game instance = null;
    private Deck gameDeck;

    /**
     * Singleton method to create or get the instance of the game
     * 
     * @return the instance of the game
     * 
     */
    public static Game getInstance() {
        if (instance == null) 
            instance = new Game();
        
        return instance;
    }

    // Delete the instance of the game
    public static void deleteInstance() {
        instance = null;
    }

    private Game() {
        
    }

    public Deck getGameDeck() {
        return gameDeck;
    }

    public void setGameDeck(Deck gameDeck) {
        this.gameDeck = gameDeck;
    }

    public void addDeck(Deck deck) {

        // If there is no game deck, set the game deck to the deck
        if (gameDeck == null) {
            gameDeck = deck;
            return;
        }

        gameDeck.mergeDecks(deck);
    }

}