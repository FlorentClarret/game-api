package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.PlatformRepository;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.AbstractEntityService;
import org.springframework.stereotype.Service;

@Service
public final class PlatformServiceImpl extends AbstractEntityService<Platform, PlatformRepository> {

    public PlatformServiceImpl(final PlatformRepository repository) {
        super(repository);
    }

}
