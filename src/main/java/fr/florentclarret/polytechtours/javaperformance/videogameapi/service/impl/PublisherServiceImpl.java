package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.PublisherNotFoundException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.PublisherRepository;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.PublisherService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public final class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(final PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> findAll() {
        final List<Publisher> platforms = this.publisherRepository.findAll();

        if (CollectionUtils.isEmpty(platforms)) {
            throw new PublisherNotFoundException("No publisher found");
        }

        return platforms;
    }

    @Override
    public Publisher findById(final Long id) {
        final Optional<Publisher> platformOptional = this.publisherRepository.findById(id);

        if (platformOptional.isEmpty()) {
            throw new PublisherNotFoundException(String.format("Publisher with id [%d] not found", id));
        }

        return platformOptional.get();
    }

}
