package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.PublisherRepository;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.AbstractEntityService;
import org.springframework.stereotype.Service;

@Service
public final class PublisherServiceImpl extends AbstractEntityService<Publisher, PublisherRepository> {

    public PublisherServiceImpl(final PublisherRepository repository) {
        super(repository);
    }

}
