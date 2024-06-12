package com.project.cardgame.entity.card;

public class Card {

    private CardSuit suit;
    private CardValue value;

    public Card(CardSuit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    //This will be used to print the card in the format "Value of Suit"
    @Override
    public String toString() {
        return value.getName() + " of " + suit.getSuit();
    }
    
}