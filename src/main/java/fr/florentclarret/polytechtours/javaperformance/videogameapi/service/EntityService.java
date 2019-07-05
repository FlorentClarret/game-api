package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;

import java.util.List;

public interface EntityService<T extends BaseEntity> {
    List<T> findAll();

    T findById(final Long id);

    T save(final T entity);

    T update(final Long id, final T entity);

    void delete(final Long id);
}
