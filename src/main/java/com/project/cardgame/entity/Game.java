package com.project.cardgame.entity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.cardgame.entity.card.Card;

//Singleton class to represent the game
public class Game {

    private static Game instance = null;
    private Deck gameDeck;
    private Map<Integer, Player> players = new HashMap<>();
    
    // Count to keep track of the number of players during addition and removal of players
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

    public void dealCard(int playerNumber, int numberOfCards) {
        Player player = players.get(playerNumber);

        gameDeck.shuffle();

        int deckSize = gameDeck.getCards().size();

        // Deal the smaller of the deck size and the number of cards requested
        for (int i = 0; i < Math.min(deckSize, numberOfCards); i++) {
            player.addToHand(gameDeck.dealCard());
        }
    }

    public List<Card> getPlayerHand(int playerNumber) {
        return players.get(playerNumber).getHand();
    }

    public List<Player> getPlayersValue() {
        // return a list of players sorted by value of hand
        return players.values().stream().sorted(Comparator.comparing(Player::getValueOfHand).reversed()).toList();
    }

}