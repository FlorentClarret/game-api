package fr.florentclarret.polytechtours.javaperformance.videogameapi.exception;

import org.springframework.http.HttpStatus;

public class EntityWithNameAlreadyExistsException extends BusinessException {

    public EntityWithNameAlreadyExistsException(final String name) {
        super(String.format("The entity with name [%s] already exists", name), HttpStatus.CONFLICT);
    }

}
