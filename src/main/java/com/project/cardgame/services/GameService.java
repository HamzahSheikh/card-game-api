package com.project.cardgame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.cardgame.ResponseHandler;
import com.project.cardgame.entity.Deck;
import com.project.cardgame.entity.Game;

@Service
public class GameService {

    private Game game;
    private Stack<Deck> reserveDecks = new Stack<>();

    @Autowired
    private ApplicationEventPublisher publisher;

    public ResponseEntity<Object> createGame() {

        if (game != null) {
            publisher.publishEvent(ResponseHandler.gameExistsEntity());
            return ResponseHandler.gameExistsEntity();
        }

        game = Game.getInstance();

        publisher.publishEvent(ResponseHandler.responseBuilder("Success! Game created!", HttpStatus.OK));

        return ResponseHandler.responseBuilder("Success! Game created!", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteGame() {

        if (game == null) {
            return ResponseHandler.noGamEntity();
        }

        Game.deleteInstance();
        game = null;
        return ResponseHandler.responseBuilder("Success! Game deleted!", HttpStatus.OK);
    }

    public ResponseEntity<Object> createDeck() {

        Deck deck = new Deck();
        reserveDecks.push(deck);

        String message = "Success! Deck Created! Total Reserve Decks: " + reserveDecks.size() + ";";

        return ResponseHandler.responseBuilder(message, reserveDecks.size(), HttpStatus.OK);
    }

    public ResponseEntity<Object> addDeck() {

        if (game == null) {
            return ResponseHandler.noGamEntity();
        }

        if (reserveDecks.isEmpty()) {
            return ResponseHandler.noReserveDeckEntity();
        }

        game.addDeck(reserveDecks.pop());

        return ResponseHandler.responseBuilder("Success! Deck added to game!", game.getGameDeck(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getGameDeck() {

        if (game == null) {
            return ResponseHandler.noGamEntity();
        }

        if (game.getGameDeck() == null) {
            return ResponseHandler.noGameDeckEntity();
        }

        return new ResponseEntity<>(game.getGameDeck(), HttpStatus.OK);
    }

    public ResponseEntity<Object> addPlayer() {

        if (game == null) {
            return ResponseHandler.noGamEntity();
        }

        game.addPlayer();

        return ResponseHandler.responseBuilder("Player " + game.getPlayerCount() + " added!", game.getPlayerCount(), HttpStatus.OK);
    }

    public ResponseEntity<Object> removePlayer(int playerNumber) {

        if (game == null) {
            return ResponseHandler.noGamEntity();
        }

        if (!game.getPlayers().containsKey(playerNumber)) {
            return ResponseHandler.playerMissingEntity();
        }

        game.removePlayer(playerNumber);

        return ResponseHandler.responseBuilder("Player " + playerNumber + " removed!", game.getPlayers().size(), HttpStatus.OK);
    }

    public ResponseEntity<Object> dealCard(int playerNumber, int numberOfCards) {

        if (game == null) {
            return ResponseHandler.noGamEntity();
        }

        if (!game.getPlayers().containsKey(playerNumber)) {
            return ResponseHandler.playerMissingEntity();
        }

        if (game.getGameDeck() == null) {
            return ResponseHandler.noGameDeckEntity();
        }

        game.dealCard(playerNumber, numberOfCards);

        return ResponseHandler.responseBuilder("Card dealt!", game.getPlayers().get(playerNumber), HttpStatus.OK);
    }

    public ResponseEntity<Object> getPlayerHand(int playerNumber) {  
            if (game == null) {
                return ResponseHandler.noGamEntity();
            }
    
            if (!game.getPlayers().containsKey(playerNumber)) {
                return ResponseHandler.playerMissingEntity();
            }
    
            return ResponseHandler.responseBuilder("Player " + playerNumber + "\'s hand, Total: " + game.getPlayers().get(playerNumber).getHand().size() + " Cards", game.getPlayers().get(playerNumber).getHand(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getPlayersValue() {
        if (game == null) {
            return ResponseHandler.noGamEntity();
        }

        if (game.getPlayers().size() == 0) {
            return ResponseHandler.noPlayer(); 
        }

        return ResponseHandler.responseBuilder("Players Value!", game.getPlayersValue(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getSuitCount() {
        if (game == null) {
            return ResponseHandler.noGamEntity();
        }

        if (game.getGameDeck() == null) {
            return ResponseHandler.noGameDeckEntity();
        }

        return ResponseHandler.responseBuilder("Suit Count!", game.getGameDeck().getSuitCount(), HttpStatus.OK);
    }



}
