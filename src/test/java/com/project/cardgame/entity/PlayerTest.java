package com.project.cardgame.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.project.cardgame.entity.card.Card;
import com.project.cardgame.entity.card.CardSuit;
import com.project.cardgame.entity.card.CardValue;

public class PlayerTest {
    
    @Test
    void testPlayer() {
        Player player = new Player("John");
        assertEquals("John", player.getName(), "Player should be named John");
    }

    @Test
    void testAddCard() {
        Player player = new Player("John");
        Card card = new Card(CardSuit.HEARTS, CardValue.ACE);
        player.addToHand(card);
        assertEquals(1, player.getHand().size(), "Player should have 1 card in hand");
    }

    @Test
    void testGetValueofHand() {
        Player player = new Player("John");
        Card card1 = new Card(CardSuit.HEARTS, CardValue.ACE);
        Card card2 = new Card(CardSuit.DIAMONDS, CardValue.TWO);
        Card card3 = new Card(CardSuit.CLUBS, CardValue.THREE);
        player.addToHand(card1);
        player.addToHand(card2);
        player.addToHand(card3);
        assertEquals(CardValue.ACE.getValue() + CardValue.TWO.getValue() + CardValue.THREE.getValue(), player.getValueOfHand());
    }
}
