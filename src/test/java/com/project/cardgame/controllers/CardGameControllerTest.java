package com.project.cardgame.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.cardgame.services.GameService;

public class CardGameControllerTest {

    @InjectMocks
    private CardGameController cardGameController;

    @Mock
    private GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGame() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Game created!", HttpStatus.CREATED);
        when(gameService.createGame()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.createGame();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Game created!", response.getBody());
    }

    @Test
    public void testDeleteGame() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Game deleted!", HttpStatus.OK);
        when(gameService.deleteGame()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.deleteGame();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Game deleted!", response.getBody());
    }

    @Test
    public void testCreateDeck() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Deck created!", HttpStatus.CREATED);
        when(gameService.createDeck()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.createDeck();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Deck created!", response.getBody());
    }

    @Test
    public void testAddDeck() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Deck added!", HttpStatus.OK);
        when(gameService.addDeck()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.addDeck();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deck added!", response.getBody());
    }

    @Test
    public void testAddPlayer() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Player added!", HttpStatus.CREATED);
        when(gameService.addPlayer()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.addPlayer();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Player added!", response.getBody());
    }

    @Test
    public void testRemovePlayer() {
        int playerNumber = 1;
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Player removed!", HttpStatus.OK);
        when(gameService.removePlayer(playerNumber)).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.removePlayer(playerNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Player removed!", response.getBody());
    }

    @Test
    public void testDealCard() {
        int playerNumber = 1;
        int numberOfCards = 2;
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Cards dealt!", HttpStatus.OK);
        when(gameService.dealCard(playerNumber, numberOfCards)).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.dealCard(playerNumber, numberOfCards);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cards dealt!", response.getBody());
    }

    @Test
    public void testGetPlayerHand() {
        int playerNumber = 1;
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Player's hand retrieved!", HttpStatus.OK);
        when(gameService.getPlayerHand(playerNumber)).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.getPlayerHand(playerNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Player's hand retrieved!", response.getBody());
    }

    @Test
    public void testGetPlayersValue() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Players' values retrieved!", HttpStatus.OK);
        when(gameService.getPlayersValue()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.getPlayersValue();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Players' values retrieved!", response.getBody());
    }

    @Test
    public void testGetDeckSuitCount() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Deck suit count retrieved!", HttpStatus.OK);
        when(gameService.getDeckSuitCount()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.getDeckSuitCount();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deck suit count retrieved!", response.getBody());
    }

    @Test
    public void testGetGameEventLog() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Game event log retrieved!", HttpStatus.OK);
        when(gameService.getGameEventLog()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.getGameEventLog();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Game event log retrieved!", response.getBody());
    }

    @Test
    public void testGetPlayerEventLog() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Player event log retrieved!", HttpStatus.OK);
        when(gameService.getPlayerEventLog()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.getPlayerEventLog();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Player event log retrieved!", response.getBody());
    }

    @Test
    public void testGetDeckEventLog() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("Deck event log retrieved!", HttpStatus.OK);
        when(gameService.getDeckEventLog()).thenReturn(responseEntity);

        ResponseEntity<Object> response = cardGameController.getDeckEventLog();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deck event log retrieved!", response.getBody());
    }
}