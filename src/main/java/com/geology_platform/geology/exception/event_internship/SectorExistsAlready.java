package com.geology_platform.geology.exception.event_internship;

public class SectorExistsAlready extends RuntimeException {
    public SectorExistsAlready(String message) {
        super(message);
    }
}
