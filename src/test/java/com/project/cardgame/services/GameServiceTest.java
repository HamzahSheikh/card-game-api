package com.project.cardgame.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.cardgame.entity.Game;
import com.project.cardgame.event.EventListener;


public class GameServiceTest {

    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private EventListener eventListener;

    @InjectMocks
    private GameService gameService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Game.deleteInstance();  // Ensure no existing singleton instance
    }

    @Test
    public void testCreateGameWhenGameExists() {
        gameService.createGame();
        ResponseEntity<Object> response = gameService.createGame();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());    
    }

    @Test
    public void testCreateGameWhenGameNotExists() {
        Game.deleteInstance();  // Ensure no existing singleton instance

        ResponseEntity<Object> response = gameService.createGame();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testDeleteGameWhenGameNotExists() {
        ResponseEntity<Object> response = gameService.deleteGame();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteGameWhenGameExists() {
        gameService.createGame();  // Create a game first
        ResponseEntity<Object> response = gameService.deleteGame();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateDeck() {
        ResponseEntity<Object> response = gameService.createDeck();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddDeckWhenGameNotExists() {
        ResponseEntity<Object> response = gameService.addDeck();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAddDeckWhenNoReserveDecks() {
        gameService.createGame();
        ResponseEntity<Object> response = gameService.addDeck();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAddDeckWhenReserveDeckExists() {
        gameService.createGame();
        gameService.createDeck();
        ResponseEntity<Object> response = gameService.addDeck();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddPlayerWhenGameNotExists() {
        ResponseEntity<Object> response = gameService.addPlayer();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAddPlayerWhenGameExists() {
        gameService.createGame();
        ResponseEntity<Object> response = gameService.addPlayer();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testRemovePlayerWhenGameNotExists() {
        ResponseEntity<Object> response = gameService.removePlayer(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testRemovePlayerWhenPlayerNotExists() {
        gameService.createGame();
        ResponseEntity<Object> response = gameService.removePlayer(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testRemovePlayerWhenPlayerExists() {
        gameService.createGame();
        gameService.addPlayer();
        ResponseEntity<Object> response = gameService.removePlayer(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDealCardWhenGameNotExists() {
        ResponseEntity<Object> response = gameService.dealCard(1, 2);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDealCardWhenPlayerNotExists() {
        gameService.createGame();
        ResponseEntity<Object> response = gameService.dealCard(1, 2);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDealCardWhenDeckNotExists() {
        gameService.createGame();
        gameService.addPlayer();
        ResponseEntity<Object> response = gameService.dealCard(1, 2);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetPlayerHandWhenGameNotExists() {
        ResponseEntity<Object> response = gameService.getPlayerHand(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetPlayerHandWhenPlayerNotExists() {
        gameService.createGame();
        ResponseEntity<Object> response = gameService.getPlayerHand(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetPlayersValueWhenGameNotExists() {
        ResponseEntity<Object> response = gameService.getPlayersValue();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetPlayersValueWhenNoPlayers() {
        gameService.createGame();
        ResponseEntity<Object> response = gameService.getPlayersValue();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetPlayersValueWhenPlayersExist() {
        gameService.createGame();
        gameService.addPlayer();
        ResponseEntity<Object> response = gameService.getPlayersValue();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
        