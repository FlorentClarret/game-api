package fr.florentclarret.polytechtours.javaperformance.videogameapi.exception;

import org.springframework.http.HttpStatus;

public final class VideoGameNotFoundException extends BusinessException {

    public VideoGameNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
