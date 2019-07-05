package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.VideoGameRepository;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.AbstractEntityService;
import org.springframework.stereotype.Service;

@Service
public final class VideoGameServiceImpl extends AbstractEntityService<VideoGame> {

    public VideoGameServiceImpl(final VideoGameRepository repository) {
        super(repository);
    }
}
