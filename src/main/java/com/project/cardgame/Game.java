package com.project.cardgame;

import java.util.HashMap;
import java.util.Map;

//Singleton class to represent the game
public class Game {

    private static Game instance = null;
    private Deck gameDeck;
    private Map<Integer, Player> players = new HashMap<>();
    private int playerCount = 0;

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

    public int getPlayerCount() {
        return playerCount;
    }

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public void addPlayer() {
        playerCount++;
        players.put(playerCount, new Player("Player " + playerCount));
    }

    public void removePlayer(int playerNumber) {
        players.remove(playerNumber);
    }

}