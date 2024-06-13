package com.project.cardgame.entity.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void testCard() {
        Card card = new Card(CardSuit.HEARTS, CardValue.ACE);
        assertEquals(card.getSuit(), CardSuit.HEARTS, "Card should be Hearts");
        assertEquals(card.getValue(), CardValue.ACE, "Card should be Ace");
    }

    @Test
    void testCardToString() {
        Card card = new Card(CardSuit.HEARTS, CardValue.ACE);
        assertEquals("Ace of Hearts", card.toString(), "Card should be Ace of Hearts");
    }   
    
}
