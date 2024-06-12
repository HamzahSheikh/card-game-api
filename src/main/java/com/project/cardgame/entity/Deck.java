package com.project.cardgame.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.project.cardgame.entity.card.Card;
import com.project.cardgame.entity.card.CardSuit;
import com.project.cardgame.entity.card.CardValue;

public class Deck {

    private List<Card> cards;
    private Map<CardSuit, Integer> suitCount;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public Deck() {
        cards = new ArrayList<>();
        suitCount = new HashMap<>();
        initializeDeck();
    }

    // Add all possible cards to the deck
    private void initializeDeck() {

        // For each suit, add all possible values to the deck
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(suit, value));
            }

            // Keep track of the number of each suit in the deck
            suitCount.put(suit, CardValue.values().length);
        }
    }

    public List<Card> getCards() {
        readLock.lock();
        try {
            // Return a copy of the cards list to prevent modification
            return new ArrayList<>(cards);
        } finally {
            readLock.unlock();
        }
    }

    // Merge another deck into the current deck
    public void mergeDecks(Deck deck) {
        writeLock.lock();
        try {
            this.cards.addAll(deck.getCards());

            // Update the suit count for each suit in the merged deck
            for (CardSuit suit : CardSuit.values()) {
                suitCount.put(suit, suitCount.getOrDefault(suit, 0) + deck.getSuitCount().getOrDefault(suit, 0));
            }
        } finally {
            writeLock.unlock();
        }
    }

    public void shuffle() {
        writeLock.lock();
        try {
            // Shuffle deck by iterating through the deck and swapping each card to a random index
            for (int i = 0; i < cards.size(); i++) {
                int randomIndex = (int) (Math.random() * cards.size());
                Card temp = cards.get(i);
                cards.set(i, cards.get(randomIndex));
                cards.set(randomIndex, temp);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public Card dealCard() {
        writeLock.lock();
        try {
            // Remove the last card from the deck to ensure O(1) time complexity
            Card card = cards.remove(cards.size() - 1);

            // Decrement the suit count for the suit of the card that was dealt
            suitCount.put(card.getSuit(), suitCount.get(card.getSuit()) - 1);

            return card;
        } finally {
            writeLock.unlock();
        }
    }

    public Map<CardSuit, Integer> getSuitCount() {
        readLock.lock();
        try {
            // Return a copy of the suit count map to prevent modification
            return new HashMap<>(suitCount);
        } finally {
            readLock.unlock();
        }
    }

}