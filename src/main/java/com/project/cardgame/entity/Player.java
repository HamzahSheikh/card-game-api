package com.project.cardgame.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.project.cardgame.entity.card.Card;

public class Player {

    private String name;
    private List<Card> hand;
    private int valueOfHand;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.valueOfHand = 0;
    }

    public String getName() {
        readLock.lock();
        try {
            return name;
        } finally {
            readLock.unlock();
        }
    }

    public void addToHand(Card card) {
        writeLock.lock();
        try {
            hand.add(card);
            valueOfHand += card.getValue().getValue();
        } finally {
            writeLock.unlock();
        }
    }

    public List<Card> getHand() {
        readLock.lock();
        try {
            return new ArrayList<>(hand);
        } finally {
            readLock.unlock();
        }
    }

    public int getValueOfHand() {
        return valueOfHand;
    }

}
