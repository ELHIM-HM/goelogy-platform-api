package com.geology_platform.geology.entity.event_internship;

import com.geology_platform.geology.exception.event_internship.InvalidLevelException;
import com.geology_platform.geology.exception.event_internship.InvalidThesisStatusException;

public enum ThesisStatus {
    AVAILABLE,
    ASSIGNED;
    public static ThesisStatus fromString(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return ThesisStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidThesisStatusException("invalid thesis status");
        }
    }
}
