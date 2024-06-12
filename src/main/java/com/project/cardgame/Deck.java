package com.project.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List <Card> cards;

    public Deck() {

        cards = new ArrayList<>();

        //for every suit of cards declared in CardSuit enum, we add all values of cards declared in CardValue enum
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(suit, value));
            }
        }

    }

    public List<Card> getCards() {
        return cards;
    }

    // add another deck of cards to the current deck
    public void mergeDecks(Deck deck) {
        this.cards.addAll(deck.getCards());
    }

    public void shuffle() {
        // Shuffle deck by iterating through the deck and swapping each card to a random index
        for (int i = 0; i < cards.size(); i++) {
            int randomIndex = (int) (Math.random() * cards.size());
            Card temp = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, temp);
        }
    }

    public Card dealCard() {
        // Remove the last card from the deck to ensure O(1) time complexity
        Card card = cards.remove(cards.size() - 1);
        return card;
    }
  
}
