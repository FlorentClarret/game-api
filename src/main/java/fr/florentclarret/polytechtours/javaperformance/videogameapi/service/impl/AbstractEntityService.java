package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.BusinessException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.EntityService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

public abstract class AbstractEntityService<T extends BaseEntity> implements EntityService<T> {

    private final JpaRepository<T, Long> repository;

    protected AbstractEntityService(final JpaRepository<T, Long> repository) {
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
        final Optional<T> entityOptional = this.repository.findById(id);

        if (entityOptional.isEmpty()) {
            throw new BusinessException(String.format("Entity with id [%d] not found", id), HttpStatus.NOT_FOUND);
        }

        return entityOptional.get();
    }
}
