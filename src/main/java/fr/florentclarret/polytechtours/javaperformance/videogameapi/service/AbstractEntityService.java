package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.BusinessException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.BaseEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class AbstractEntityService<T extends BaseEntity> implements EntityService<T> {

    protected final BaseEntityRepository<T> repository;

    public AbstractEntityService(final BaseEntityRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        final List<T> entities = this.repository.findAll();

        if (CollectionUtils.isEmpty(entities)) {
            throw new BusinessException("No entity found", HttpStatus.NOT_FOUND);
        }

        return entities;
    }

    @Override
    public T findById(final Long id) {
        return this.repository.findById(id).orElseThrow(() -> new BusinessException(String.format("Entity with id [%d] not found", id), HttpStatus.NOT_FOUND));
    }

    @Override
    public T save(final T entity) {
        this.repository.findByName(entity.getName()).ifPresent(s -> {
            throw new BusinessException(String.format("The entity with name [%s] already exists", entity.getName()), HttpStatus.CONFLICT);
        });

        return this.repository.save(entity);
    }

    @Override
    public T update(final Long id, final T entity) {
        this.repository.findByName(entity.getName()).ifPresent(s -> {
            throw new BusinessException(String.format("The entity with name [%s] already exists", entity.getName()), HttpStatus.CONFLICT);
        });

        final T oldEntity = this.repository.findById(id).orElseThrow(() -> new BusinessException(String.format("Entity with id [%d] not found", id), HttpStatus.NOT_FOUND));
        oldEntity.setName(entity.getName());

        return this.repository.save(oldEntity);
    }

    @Override
    public void delete(final Long id) {
        this.repository.delete(this.repository.findById(id).orElseThrow(() -> new BusinessException(String.format("Entity with id [%d] not found", id), HttpStatus.NOT_FOUND)));
    }
}
