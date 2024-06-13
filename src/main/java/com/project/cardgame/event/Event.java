package com.project.cardgame.event;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEvent;
import org.springframework.http.ResponseEntity;

public class Event extends ApplicationEvent{

    private EventType eventType;
    private String eventAction;
    private LocalDateTime eventTime;
    private ResponseEntity<Object> description;

    public Event(EventType type, String eventAction, ResponseEntity<Object> response) {
        super(type);
        this.eventType = type;
        this.eventAction = eventAction;
        this.description = response;
        this.eventTime = LocalDateTime.now();
    }

    public EventType getEventType() {
        return eventType;
    }

    public ResponseEntity<Object> getDescription() {
        return description;
    }

    public String getEventAction() {
        return eventAction;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }


}
