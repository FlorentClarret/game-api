package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;

import java.util.List;

public interface Controller<T extends BaseEntity> {

    T one(final Long id);

    List<T> all();
}
