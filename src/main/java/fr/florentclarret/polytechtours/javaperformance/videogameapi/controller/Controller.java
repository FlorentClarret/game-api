package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

public interface Controller<T extends BaseEntity> {

    Resource<T> one(Long id);

    Resources<Resource<T>> all();

    Resource<T> create(T entity);

    Resource<T> update(Long id, T entity);

    ResponseEntity<Void> delete(Long id);
}
