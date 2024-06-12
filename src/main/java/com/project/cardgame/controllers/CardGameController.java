package com.project.cardgame.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cardgame.Game;

@RestController
public class CardGameController {

    private Game game;


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

}