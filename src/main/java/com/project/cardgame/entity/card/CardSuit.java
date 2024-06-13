package com.project.cardgame.entity.card;

public enum CardSuit {

    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades");

    private String suit;

    private CardSuit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
    
}
