package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

public interface Controller<T extends BaseEntity> {

    Resource<T> one(final Long id);

    Resources<Resource<T>> all();

    Resource<T> create(final T entity);

    Resource<T> update(final Long id, final T entity);

    ResponseEntity<Void> delete(final Long id);
}
