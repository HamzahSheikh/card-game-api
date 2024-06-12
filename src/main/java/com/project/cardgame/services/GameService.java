package com.project.cardgame.services;

import java.util.Stack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.cardgame.Deck;
import com.project.cardgame.Game;
import com.project.cardgame.ResponseHandler;

@Service
public class GameService {

    private Game game;
    private Stack<Deck> reserveDecks = new Stack<>();

    public ResponseEntity<Object> createGame() {

        if (game != null) {
            return ResponseHandler.gameExistsEntity();
        }

        game = Game.getInstance();

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

}
