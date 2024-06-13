package com.project.cardgame.event;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EventTest {

    private EventType eventType;
    private String eventAction;
    private ResponseEntity<Object> description;
    private Event event;

    @BeforeEach
    public void setUp() {
        eventType = EventType.GAME_EVENT;
        eventAction = "CREATE_GAME";
        description = new ResponseEntity<>("Success! Game created!", HttpStatus.CREATED);
        event = new Event(eventType, eventAction, description);
    }

    @Test
    public void testEventConstructor() {
        assertNotNull(event);
        assertEquals(eventType, event.getEventType());
        assertEquals(eventAction, event.getEventAction());
        assertEquals(description, event.getDescription());
        assertNotNull(event.getEventTime());
    }

    @Test
    public void testGetEventType() {
        assertEquals(eventType, event.getEventType());
    }

    @Test
    public void testGetEventAction() {
        assertEquals(eventAction, event.getEventAction());
    }

    @Test
    public void testGetDescription() {
        assertEquals(description, event.getDescription());
    }

}