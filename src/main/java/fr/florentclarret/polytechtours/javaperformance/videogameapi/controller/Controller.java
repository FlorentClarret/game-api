package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

public interface Controller<T extends BaseEntity> {

    Resource<T> one(final Long id);

    Resources<Resource<T>> all();
}
