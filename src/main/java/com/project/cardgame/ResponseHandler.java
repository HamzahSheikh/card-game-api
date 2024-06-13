package com.project.cardgame;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    
    public static ResponseEntity<Object> responseBuilder(String message, Object data, HttpStatus httpStatus) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        response.put("status", httpStatus.value());
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", httpStatus.value());

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> noGamEntity() {
        return responseBuilder("No game created!", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> gameExistsEntity() {
        return responseBuilder("Game already exists!", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> noGameDeckEntity() {
        return responseBuilder("No Game Deck!", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> noReserveDeckEntity() {
        return responseBuilder("No reserve deck created!", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> playerMissingEntity(int player) {
        return responseBuilder("Player " + player + " Does Not Exist!", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> noPlayer() {
        return responseBuilder("No Player in Game!", HttpStatus.BAD_REQUEST);
    }
 
}
