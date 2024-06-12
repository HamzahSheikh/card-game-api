package com.project.cardgame.entity;

import java.util.ArrayList;
import java.util.List;

import com.project.cardgame.entity.card.Card;

public class Player {

    private String name;
    private List<Card> hand;
    private int valueOfHand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.valueOfHand = 0;
    }

    public String getName() {
        return name;
    }

    public void addToHand(Card card) {
        hand.add(card);
        valueOfHand += card.getValue().getValue();
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getValueOfHand() {
        return valueOfHand;
    }
   
}
