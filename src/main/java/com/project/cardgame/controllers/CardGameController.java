package com.project.cardgame.controllers;


import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cardgame.Deck;
import com.project.cardgame.Game;
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
     * @return 200 OK if the game was created, 400 BAD REQUEST if a game already exists
     */
    @PostMapping(value = "/create/game")
    public ResponseEntity<Object> createGame() {
        return gameService.createGame();
    }

    /**
     * Delete the game if one exists
     * 
     * @return 200 OK if the game was deleted, 400 BAD REQUEST if no game exists
     */
    @PostMapping("/delete/game")
    public ResponseEntity<Object> deleteGame() {
        return gameService.deleteGame();
    }

    /**
     * Create a new deck and add it to the stack of reserve decks
     * 
     * @return 200 OK if the deck was created, 400 BAD REQUEST if no game exists
     */
    @PostMapping("/create/deck")
    public ResponseEntity<Object> createDeck() {
        return gameService.createDeck();
    }

    /**
     * Add a deck to the game deck
     * 
     * @return 200 OK if the deck was added, 400 BAD REQUEST if no game exists, 400 BAD REQUEST if no reserve deck exists
     */
    @PostMapping("/add/deck")
    public ResponseEntity<Object> addDeck() {
        return gameService.addDeck();
    }

}