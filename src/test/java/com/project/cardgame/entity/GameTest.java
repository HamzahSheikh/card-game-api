package com.project.cardgame.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        Game.deleteInstance(); // Ensure we start with a fresh instance
        game = Game.getInstance();
    }

    @AfterEach
    public void tearDown() {
        Game.deleteInstance(); // Clean up after each test
    }

    @Test
    public void testSingletonInstance() {
        Game instance1 = Game.getInstance();
        Game instance2 = Game.getInstance();
        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    public void testDeleteInstance() {
        Game instance1 = Game.getInstance();
        Game.deleteInstance();
        Game instance2 = Game.getInstance();
        assertNotSame(instance1, instance2, "Instances should be different after deletion and recreation");
    }

    @Test
    public void testAddAndRemovePlayer() {
        game.addPlayer();
        assertEquals(1, game.getPlayerCount(), "Player count should be 1 after adding one player");
        assertEquals("Player 1", game.getPlayers().get(1).getName(), "Player 1 should be named 'Player 1'");

        game.addPlayer();
        assertEquals(2, game.getPlayerCount(), "Player count should be 2 after adding another player");
        assertEquals("Player 2", game.getPlayers().get(2).getName(), "Player 2 should be named 'Player 2'");

        game.removePlayer(1);
        assertEquals(1, game.getPlayers().size(), "Player count should be 1 after removing one player");
        assertNull(game.getPlayers().get(1), "Player 1 should be removed");
    }

    @Test
    public void testDealCards() {
        game.addPlayer();
        game.setGameDeck(new Deck());
        game.dealCard(1, 5);
        assertEquals(5, game.getPlayers().get(1).getHand().size(), "Player 1 should have 5 cards in hand");   
    }

}