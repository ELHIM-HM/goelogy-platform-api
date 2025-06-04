package com.geology_platform.geology.entity.event_internship;

import com.geology_platform.geology.exception.event_internship.InvalidLevelException;

public enum Level {
    LICENCE,
    MASTER,
    DOCTORAT;

    public static Level fromString(String value) {
        if (value ==null || value.isBlank()) return null;
        try {
            System.out.println("level en maj:"+value.toUpperCase());
            return Level.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidLevelException("invalid level");
        }
    }
}
