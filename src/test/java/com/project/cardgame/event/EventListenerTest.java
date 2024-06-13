package com.project.cardgame.event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EventListenerTest {

    private EventListener eventListener;
    private EventType eventType;
    private Event event;

    @BeforeEach
    public void setUp() {
        eventListener = new EventListener();
        eventType = EventType.GAME_EVENT;
        ResponseEntity<Object> response = new ResponseEntity<>("Success! Game created!", HttpStatus.CREATED);
        event = new Event(eventType, "CREATE_GAME", response);
    }

    @Test
    public void testAddEvent() {
        eventListener.addEvent(event);

        List<Event> events = eventListener.getEvents(eventType);
        assertNotNull(events);
        assertEquals(1, events.size());
        assertEquals(event, events.get(0));
    }

    @Test
    public void testGetEvents() {
        eventListener.addEvent(event);

        List<Event> events = eventListener.getEvents(eventType);
        assertNotNull(events);
        assertEquals(1, events.size());
        assertEquals(event, events.get(0));

        // Add another event of the same type and check if both are retrieved
        ResponseEntity<Object> response = new ResponseEntity<>("Success! Game created!", HttpStatus.CREATED);
        Event anotherEvent = new Event(eventType, "CREATE_GAME", response);
        eventListener.addEvent(anotherEvent);

        events = eventListener.getEvents(eventType);
        assertNotNull(events);
        assertEquals(2, events.size());
        assertTrue(events.contains(event));
        assertTrue(events.contains(anotherEvent));
    }

    @Test
    public void testGetEventsForNonExistingType() {
        List<Event> events = eventListener.getEvents(EventType.PLAYER_EVENT);
        assertNull(events);
    }

}