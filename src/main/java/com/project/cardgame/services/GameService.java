package com.project.cardgame.services;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.cardgame.ResponseHandler;
import com.project.cardgame.entity.Deck;
import com.project.cardgame.entity.Game;
import com.project.cardgame.event.Event;
import com.project.cardgame.event.EventListener;
import com.project.cardgame.event.EventType;

@Service
public class GameService {

    private Game game;
    private Stack<Deck> reserveDecks = new Stack<>();

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EventListener eventListener;

    public ResponseEntity<Object> createGame() {

        ResponseEntity<Object> response;
        String action = "CREATE_GAME";

        if (game != null) {

            response = ResponseHandler.gameExistsEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            return response;
        }

        game = Game.getInstance();

        response = ResponseHandler.responseBuilder("Success! Game created!", HttpStatus.CREATED);
        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> deleteGame() {

        ResponseEntity<Object> response;
        String action = "DELETE_GAME";

        if (game == null) {
            response = ResponseHandler.noGamEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            return response;
        }

        Game.deleteInstance();
        game = null;

        response = ResponseHandler.responseBuilder("Success! Game deleted!", HttpStatus.OK);
        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> createDeck() {

        ResponseEntity<Object> response;
        String action = "CREATE_DECK";

        Deck deck = new Deck();
        reserveDecks.push(deck);

        String message = "Success! Deck Created! Total Reserve Decks: " + reserveDecks.size() + ";";

        response = ResponseHandler.responseBuilder(message, reserveDecks.size(), HttpStatus.CREATED);
        publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> addDeck() {

        ResponseEntity<Object> response;
        String action = "ADD_DECK";

        if (game == null) {
            response = ResponseHandler.noGamEntity();

            publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));

            return response;
        }

        if (reserveDecks.isEmpty()) {
            response = ResponseHandler.noReserveDeckEntity();

            publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));

            return response;
        }

        game.addDeck(reserveDecks.pop());

        response = ResponseHandler.responseBuilder("Success! Deck added to game!", game.getGameDeck(), HttpStatus.OK);

        publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));
        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> getGameDeck() {

        ResponseEntity<Object> response;
        String action = "GET_GAME_DECK";

        if (game == null) {

            response = ResponseHandler.noGamEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));

            return response;
        }

        if (game.getGameDeck() == null) {

            response = ResponseHandler.noGameDeckEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));

            return response;
        }

        response = new ResponseEntity<>(game.getGameDeck(), HttpStatus.OK);
        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> addPlayer() {

        ResponseEntity<Object> response;
        String action = "ADD_PLAYER";

        if (game == null) {
            response = ResponseHandler.noGamEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));
            return response;
        }

        game.addPlayer();

        response = ResponseHandler.responseBuilder("Player " + game.getPlayerCount() + " added!", game.getPlayerCount(),
                HttpStatus.CREATED);

        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
        publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> removePlayer(int playerNumber) {

        ResponseEntity<Object> response;
        String action = "REMOVE_PLAYER";

        if (game == null) {
            response = ResponseHandler.noGamEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));
            return response;
        }

        if (!game.getPlayers().containsKey(playerNumber)) {
            response = ResponseHandler.playerMissingEntity(playerNumber);
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));
            return response;
        }

        game.removePlayer(playerNumber);

        response = ResponseHandler.responseBuilder("Player " + playerNumber + " removed!", game.getPlayers().size(),
                HttpStatus.OK);

        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
        publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> dealCard(int playerNumber, int numberOfCards) {

        ResponseEntity<Object> response;
        String action = "DEAL_CARD";

        if (game == null) {
            response = ResponseHandler.noGamEntity();

            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));

            return response;
        }

        if (!game.getPlayers().containsKey(playerNumber)) {
            response = ResponseHandler.playerMissingEntity(playerNumber);
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));

            return response;
        }

        if (game.getGameDeck() == null) {

            response = ResponseHandler.noGameDeckEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

            return response;
        }

        game.dealCard(playerNumber, numberOfCards);

        response = ResponseHandler.responseBuilder("Card(s) dealt!", game.getPlayers().get(playerNumber),
                HttpStatus.OK);

        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
        publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));
        publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> getPlayerHand(int playerNumber) {

        ResponseEntity<Object> response;
        String action = "GET_PLAYER_HAND";

        if (game == null) {
            response = ResponseHandler.noGamEntity();

            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

            return response;
        }

        if (!game.getPlayers().containsKey(playerNumber)) {
            response = ResponseHandler.playerMissingEntity(playerNumber);

            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

            return response;
        }

        response = ResponseHandler.responseBuilder("Player " + playerNumber + "\'s hand, Total: "
                + game.getPlayers().get(playerNumber).getHand().size() + " Cards",
                game.getPlayers().get(playerNumber).getHand(), HttpStatus.OK);

        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
        publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> getPlayersValue() {

        ResponseEntity<Object> response;
        String action = "GET_PLAYERS_VALUE";

        if (game == null) {
            response = ResponseHandler.noGamEntity();

            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

            return response;
        }

        if (game.getPlayers().size() == 0) {
            response = ResponseHandler.noPlayer();

            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

            return response;
        }

        response = ResponseHandler.responseBuilder("Players Value!", game.getPlayersValue(), HttpStatus.OK);

        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
        publisher.publishEvent(new Event(EventType.PLAYER_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> getDeckSuitCount() {

        ResponseEntity<Object> response;
        String action = "GET_SUIT_COUNT_OF_DECK";

        if (game == null) {

            response = ResponseHandler.noGamEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));

            return response;
        }

        if (game.getGameDeck() == null) {

            response = ResponseHandler.noGameDeckEntity();
            publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
            publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));

            return response;
        }

        response = ResponseHandler.responseBuilder("Suit Count!", game.getGameDeck().getSuitCount(), HttpStatus.OK);

        publisher.publishEvent(new Event(EventType.GAME_EVENT, action, response));
        publisher.publishEvent(new Event(EventType.DECK_EVENT, action, response));

        return response;
    }

    public ResponseEntity<Object> getGameEventLog() {

        ResponseEntity<Object> response;

        response = ResponseHandler.responseBuilder("Game Event Log!", eventListener.getEvents(EventType.GAME_EVENT),
                HttpStatus.OK);

        return response;
    }

    public ResponseEntity<Object> getPlayerEventLog() {

        ResponseEntity<Object> response;

        response = ResponseHandler.responseBuilder("Player Event Log!", eventListener.getEvents(EventType.PLAYER_EVENT),
                HttpStatus.OK);

        return response;
    }

    public ResponseEntity<Object> getDeckEventLog() {

        ResponseEntity<Object> response;

        response = ResponseHandler.responseBuilder("Game Event Log!", eventListener.getEvents(EventType.DECK_EVENT),
                HttpStatus.OK);

        return response;
    }

}
