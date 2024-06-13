package com.project.cardgame.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
@Component
public class EventListener implements ApplicationListener {

    private final Map<EventType, List<Event>> eventHistory = new HashMap<>();

    @SuppressWarnings("null")
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof Event) {
            addEvent((Event) event);
        }
    }

    public synchronized void addEvent(Event event) {
        if (!eventHistory.containsKey(event.getEventType())) {
            eventHistory.put(event.getEventType(), new ArrayList<>());
        }
        eventHistory.get(event.getEventType()).add(event);
    }

    public synchronized List<Event> getEvents(EventType eventType) {
        if (eventHistory.containsKey(eventType)) {
            return eventHistory.get(eventType);
        }
        return null;
    }
    
}
