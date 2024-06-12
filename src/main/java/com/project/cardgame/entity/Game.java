package com.project.cardgame.entity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.project.cardgame.entity.card.Card;

//Singleton class to represent the game
public class Game {

    private static Game instance = null;
    private Deck gameDeck;
    private Map<Integer, Player> players = new HashMap<>();
    
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();


    // Count to keep track of the number of players during addition and removal of
    // players
    private int playerCount = 0;

    /**
     * Singleton method to create or get the instance of the game
     * 
     * @return the instance of the game
     * 
     */
    public static Game getInstance() {
        if (instance == null) {
            synchronized (Game.class) {
                if (instance == null) {
                    instance = new Game();
                }
            }
        }

        return instance;
    }

    // Delete the instance of the game
    public static void deleteInstance() {
        synchronized (Game.class) {
            instance = null;
        }
    }

    private Game() {

    }

    public Deck getGameDeck() {
        readLock.lock();
        try {
            return gameDeck;
        } finally {
            readLock.unlock();
        }
    }

    public void setGameDeck(Deck gameDeck) {
        writeLock.lock();
        try {
            this.gameDeck = gameDeck;
        } finally {
            writeLock.unlock();
        }
    }

    public void addDeck(Deck deck) {

        writeLock.lock();
        try {
            // If there is no game deck, set the game deck to the deck
            if (gameDeck == null) {
                gameDeck = deck;
                return;
            }

            gameDeck.mergeDecks(deck);
        } finally {
            writeLock.unlock();
        }
    }

    public int getPlayerCount() {
        readLock.lock();
        try {
            return playerCount;
        } finally {
            readLock.unlock();
        }
    }

    public Map<Integer, Player> getPlayers() {

        readLock.lock();
        try {
            return players;
        } finally {
            readLock.unlock();
        }
    }

    public void addPlayer() {

        writeLock.lock();
        try {
            playerCount++;
            players.put(playerCount, new Player("Player " + playerCount));
        } finally {
            writeLock.unlock();
        }
    }

    public void removePlayer(int playerNumber) {

        writeLock.lock();
        try {
            players.remove(playerNumber);
        } finally {
            writeLock.unlock();
        }
    }

    public void dealCard(int playerNumber, int numberOfCards) {

        writeLock.lock();
        try {
            Player player = players.get(playerNumber);

            gameDeck.shuffle();

            int deckSize = gameDeck.getCards().size();

            // Deal the smaller of the deck size and the number of cards requested
            for (int i = 0; i < Math.min(deckSize, numberOfCards); i++) {
                player.addToHand(gameDeck.dealCard());
            }
        } finally {
            writeLock.unlock();
        }

    }

    public List<Card> getPlayerHand(int playerNumber) {
        readLock.lock();
        try {
            return players.get(playerNumber).getHand();
        } finally {
            readLock.unlock();
        }
    }

    public List<Player> getPlayersValue() {

        readLock.lock();
        try {
            // return a list of players sorted by value of hand
            return players.values().stream().sorted(Comparator.comparing(Player::getValueOfHand).reversed()).toList();
        } finally {
            readLock.unlock();
        }
        
    }
}