package com.project.cardgame.event;

public enum EventType {
    GAME_EVENT("Game Event"),
    PLAYER_EVENT("Player Event"),
    DECK_EVENT("Deck Event");

    private String type;

    private EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
}
