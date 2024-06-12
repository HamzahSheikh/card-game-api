package com.project.cardgame.controllers;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cardgame.Deck;
import com.project.cardgame.Game;
import com.project.cardgame.ResponseHandler;
import com.project.cardgame.services.GameService;

@RestController
public class CardGameController {

    @Autowired
    GameService gameService;

    // Test endpoint
    @RequestMapping("/test")
    public String test() {

        return "This is the Card Game REST API! Hello World!";
    }

    /**
     * Create a new game if one does not already exist
     * 
     * @return 200 OK if the game was created, 
     *         400 BAD REQUEST if a game already exists
     *         
     */
    @PostMapping(value = "/create/game")
    public ResponseEntity<Object> createGame() {
        return gameService.createGame();
    }

    /**
     * Delete the game if one exists
     * 
     * @return 200 OK if the game was deleted, 
     *         400 BAD REQUEST if no game exists
     */
    @PostMapping("/delete/game")
    public ResponseEntity<Object> deleteGame() {
        return gameService.deleteGame();
    }

    /**
     * Create a new deck and add it to the stack of reserve decks
     * 
     * @return 200 OK if the deck was created, 
     *         400 BAD REQUEST if no game exists
     */
    @PostMapping("/create/deck")
    public ResponseEntity<Object> createDeck() {
        return gameService.createDeck();
    }

    /**
     * Add a deck to the game deck
     * 
     * @return 200 OK if the deck was added, 
     *         400 BAD REQUEST if no game exists, 
     *         400 BAD REQUEST if no reserve deck exists     
     */
    @PostMapping("/add/deck")
    public ResponseEntity<Object> addDeck() {
        return gameService.addDeck();
    }

    /**
     * Add a player to the game
     * 
     * @return 200 OK if the player was added, 
     *         400 BAD REQUEST if no game exists
     */
    @PostMapping("/game/add/players")
    public ResponseEntity<Object> addPlayer() {
        return gameService.addPlayer();
    }

    /**
     * Remove a player from the game
     * 
     * @return 200 OK if the player was removed, 
     *         400 BAD REQUEST if no game exists,
     *         400 BAD REQUEST if the player does not exist
     */
    @PostMapping("/game/remove/players/{playerNumber}")
    public ResponseEntity<Object> removePlayer(@PathVariable int playerNumber) {
        return gameService.removePlayer(playerNumber);
    }

    /**
     * Deal a card to a player
     * 
     * @return 200 OK if the card was dealt, 
     *         400 BAD REQUEST if no game exists, 
     *         400 BAD REQUEST if the player does not exist, 
     *         400 BAD REQUEST if no Game Deck
     */
    @PostMapping("/game/deck/deal/{playerNumber}/{numberOfCards}")
    public ResponseEntity<Object> dealCard(@PathVariable int playerNumber, @PathVariable int numberOfCards) {
        return gameService.dealCard(playerNumber, numberOfCards);
    }

    /**
     * Get a player's hand
     * 
     * @return 200 OK if the player's hand was retrieved, 
     *         400 BAD REQUEST if no game exists, 
     *         400 BAD REQUEST if the player does not exist
     */
    @GetMapping("/game/hand/players/{playerNumber}")
    public ResponseEntity<Object> getPlayerHand(@PathVariable int playerNumber) {
        return gameService.getPlayerHand(playerNumber);
    }

    /**
     * Get all players in the game and their hands value
     * 
     * @return 200 OK if the players were retrieved, 
     *         400 BAD REQUEST if no game, 
     *         400 BAD REQUEST if no players
     */
    @GetMapping("game/players/all/value")
    public ResponseEntity<Object> getPlayersValue() {
        return gameService.getPlayersValue();
    }

}