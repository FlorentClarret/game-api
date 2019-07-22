package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;

import java.util.List;

public interface EntityService<T extends BaseEntity> {
    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    T update(Long id, T entity);

    void delete(Long id);

    T findByName(String name);

    T findRandom();
}
