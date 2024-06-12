package com.project.cardgame.controllers;


import java.util.Stack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cardgame.Deck;
import com.project.cardgame.Game;

@RestController
public class CardGameController {

    private Game game;
    private Stack<Deck> reserveDecks = new Stack<>();


    // Test endpoint
    @RequestMapping("/test")
    public String test() {

        return "This is the Card Game REST API! Hello World!";
    }

    /**
     * Create a new game if one does not already exist
     * 
     * @return 200 OK if the game was created, 400 BAD REQUEST if a game already exists
     */
    @PostMapping(value = "/create/game")
    public ResponseEntity<Object> createGame() {

        if (game != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        game = Game.getInstance();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete the game if one exists
     * 
     * @return 200 OK if the game was deleted, 400 BAD REQUEST if no game exists
     */
    @PostMapping("/delete/game")
    public ResponseEntity<Object> deleteGame() {

        if (game == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Game.deleteInstance();
        game = null;

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create a new deck and add it to the stack of reserve decks
     * 
     * @return 200 OK if the deck was created, 400 BAD REQUEST if no game exists
     */
    @PostMapping("/create/deck")
    public ResponseEntity<Object> createDeck() {

        Deck deck = new Deck();
        reserveDecks.push(deck);

        return new ResponseEntity<>(reserveDecks.size(), HttpStatus.OK);
    }

    /**
     * Add a deck to the game deck
     * 
     * @return 200 OK if the deck was added, 400 BAD REQUEST if no game exists, 400 BAD REQUEST if no reserve deck exists
     */
    @PostMapping("/add/deck")
    public ResponseEntity<Object> addDeck() {

        if (game == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (reserveDecks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        game.addDeck(reserveDecks.pop());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This is a test endpoint to validate the game deck
     * 
     * Get the game deck
     * 
     * @return 200 OK if the deck was returned, 400 BAD REQUEST if no game exists, 400 BAD REQUEST if no deck exists
     */
    @GetMapping("/get/game-deck")
    public ResponseEntity<Object> getGameDeck() {

        if (game == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (game.getGameDeck() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(game.getGameDeck(), HttpStatus.OK);
    }


}