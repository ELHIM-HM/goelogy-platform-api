package com.geology_platform.geology.exception.event_internship;

public class EventCategoryExistsAlready extends RuntimeException {
    public EventCategoryExistsAlready(String message) {
        super(message);
    }
}
