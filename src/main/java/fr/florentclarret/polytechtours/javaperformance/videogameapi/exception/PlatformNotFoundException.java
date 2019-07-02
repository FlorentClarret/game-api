package fr.florentclarret.polytechtours.javaperformance.videogameapi.exception;

import org.springframework.http.HttpStatus;

public final class PlatformNotFoundException extends BusinessException {

    public PlatformNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
