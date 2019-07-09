package fr.florentclarret.polytechtours.javaperformance.videogameapi.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException() {
        this("No entity found");
    }

    public EntityNotFoundException(final Long id) {
        this(String.format("Entity with id [%d] not found", id));
    }

    public EntityNotFoundException(final String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
