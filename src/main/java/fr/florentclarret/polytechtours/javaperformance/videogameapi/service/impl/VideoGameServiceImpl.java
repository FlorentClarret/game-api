package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.BusinessException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.VideoGameRepository;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.AbstractEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public final class VideoGameServiceImpl extends AbstractEntityService<VideoGame> {

    public VideoGameServiceImpl(final VideoGameRepository repository) {
        super(repository);
    }

    @Override
    public VideoGame update(final Long id, final VideoGame entity) {
        this.repository.findByName(entity.getName()).ifPresent(s -> {
            throw new BusinessException(String.format("The entity with name [%s] already exists", entity.getName()), HttpStatus.CONFLICT);
        });

        final VideoGame oldEntity = this.repository.findById(id).orElseThrow(() -> new BusinessException(String.format("Entity with id [%d] not found", id), HttpStatus.NOT_FOUND));

        if (entity.getName() != null) {
            oldEntity.setName(entity.getName());
        }

        if (entity.getYear() != null) {
            oldEntity.setYear(entity.getYear());
        }

        if (entity.getCriticScore() != null) {
            oldEntity.setCriticScore(entity.getCriticScore());
        }

        if (entity.getUserScore() != null) {
            oldEntity.setUserScore(entity.getUserScore());
        }

        if (entity.getGlobalSales() != null) {
            oldEntity.setGlobalSales(entity.getGlobalSales());
        }

        return this.repository.save(oldEntity);
    }
}
