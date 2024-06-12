package com.project.cardgame;

//Singleton class to represent the game
public class Game {

    private static Game instance = null;

    /**
     * Singleton method to create or get the instance of the game
     * 
     * @return the instance of the game
     * 
     */
    public static Game getInstance() {
        if (instance == null) 
            instance = new Game();
        
        return instance;
    }

    // Delete the instance of the game
    public static void deleteInstance() {
        instance = null;
    }

    private Game() {
        
    }

}