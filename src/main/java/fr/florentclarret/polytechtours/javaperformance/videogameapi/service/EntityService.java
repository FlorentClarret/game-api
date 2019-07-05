package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;

import java.util.List;

public interface EntityService<T extends BaseEntity> {
    List<T> findAll();

    T findById(Long id);
}
