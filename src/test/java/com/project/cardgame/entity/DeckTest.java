package com.project.cardgame.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.project.cardgame.entity.card.CardSuit;
import com.project.cardgame.entity.card.CardValue;

public class DeckTest {

    @Test
    void testDeck() {
        Deck deck = new Deck();
        assertEquals(CardSuit.values().length*CardValue.values().length, deck.getCards().size());
    }

    @Test
    void testMergeDeck() {
        Deck deck = new Deck();
        Deck deck2 = new Deck();
        deck.mergeDecks(deck2);
        assertEquals(CardSuit.values().length*CardValue.values().length*2, deck.getCards().size());
    }

    @Test
    void testDealCard() {
        Deck deck = new Deck();
        deck.dealCard();
        assertEquals(CardSuit.values().length*CardValue.values().length-1, deck.getCards().size());
    }

    @Test
    void testGetSuitCount() {
        Deck deck = new Deck();
        
        // Randomly select a suit
        CardSuit suit = CardSuit.values()[(int)(Math.random()*CardSuit.values().length)];

        // Count the number of cards in the deck with the selected suit
        int count = 0;

        for (int i = 0; i < deck.getCards().size(); i++) {
            if (deck.getCards().get(i).getSuit() == suit) {
                count++;
            }
        }

        assertEquals(count, deck.getSuitCount().get(suit));

    }

    
    
}
