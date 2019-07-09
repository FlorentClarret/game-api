package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

public interface Controller<T extends BaseEntity> {

    Resource<T> one(Long id);

    Resources<Resource<T>> all();

    ResponseEntity<Resource<T>> create(T entity, UriComponentsBuilder builder) throws URISyntaxException;

    ResponseEntity<Resource<T>> update(Long id, T entity) throws URISyntaxException;

    ResponseEntity<Void> delete(Long id);
}
