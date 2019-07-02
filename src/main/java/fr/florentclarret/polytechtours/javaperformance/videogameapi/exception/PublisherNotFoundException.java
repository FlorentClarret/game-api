package fr.florentclarret.polytechtours.javaperformance.videogameapi.exception;

import org.springframework.http.HttpStatus;

public final class PublisherNotFoundException extends BusinessException {

    public PublisherNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
