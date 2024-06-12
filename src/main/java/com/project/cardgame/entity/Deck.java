package com.project.cardgame.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.cardgame.entity.card.Card;
import com.project.cardgame.entity.card.CardSuit;
import com.project.cardgame.entity.card.CardValue;

public class Deck {

    private List <Card> cards;
    private Map<CardSuit, Integer> suitCount;

    public Deck() {

        cards = new ArrayList<>();
        suitCount = new HashMap<>();

        //for every suit of cards declared in CardSuit enum, we add all values of cards declared in CardValue enum
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(suit, value));
            }

            //add the number of cards in each suit to the suitCount map
            suitCount.put(suit, CardValue.values().length);
        }

    }

    public List<Card> getCards() {
        return cards;
    }

    // add another deck of cards to the current deck
    public void mergeDecks(Deck deck) {
        this.cards.addAll(deck.getCards());

        //update the suit count for each suit in the deck
        for(CardSuit suit : CardSuit.values()) {
            suitCount.put(suit, suitCount.get(suit) + deck.suitCount.get(suit));
        }
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

        // Decrement the suit count for the suit of the card that was dealt
        suitCount.put(card.getSuit(), suitCount.get(card.getSuit()) - 1);
        return card;
    }

    // get the suit count of the deck
    public Map<CardSuit, Integer> getSuitCount() {
        return suitCount;
    }
  
}
