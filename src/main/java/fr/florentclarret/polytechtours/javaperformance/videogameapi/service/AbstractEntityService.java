package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.EntityNotFoundException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.EntityWithNameAlreadyExistsException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.BaseEntityRepository;
import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class AbstractEntityService<T extends BaseEntity, U extends BaseEntityRepository<T>> implements EntityService<T> {

    private final U repository;

    public AbstractEntityService(final U repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        final List<T> entities = this.repository.findAll();

        if (CollectionUtils.isEmpty(entities)) {
            throw new EntityNotFoundException();
        }

        return entities;
    }

    @Override
    public T findById(final Long id) {
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public T save(final T entity) {
        this.repository.findByName(entity.getName()).ifPresent(s -> {
            throw new EntityWithNameAlreadyExistsException(entity.getName());
        });

        return this.repository.save(entity);
    }

    @Override
    public T update(final Long id, final T entity) {
        this.repository.findByName(entity.getName()).ifPresent(s -> {
            throw new EntityWithNameAlreadyExistsException(entity.getName());
        });

        final T oldEntity = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        oldEntity.setName(entity.getName());
        return this.repository.save(oldEntity);
    }

    @Override
    public void delete(final Long id) {
        this.repository.delete(this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public T findByName(final String name) {
        return this.repository.findByName(name).orElseThrow(() -> new EntityWithNameAlreadyExistsException(name));
    }

    protected U getRepository() {
        return repository;
    }
}
