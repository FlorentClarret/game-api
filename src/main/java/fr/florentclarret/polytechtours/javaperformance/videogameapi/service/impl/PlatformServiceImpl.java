package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.PlatformNotFoundException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.PlatformRepository;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.PlatformService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public final class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;

    public PlatformServiceImpl(final PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public List<Platform> findAll() {
        final List<Platform> platforms = this.platformRepository.findAll();

        if (CollectionUtils.isEmpty(platforms)) {
            throw new PlatformNotFoundException("No platform found");
        }

        return platforms;
    }

    @Override
    public Platform findById(final Long id) {
        final Optional<Platform> platformOptional = this.platformRepository.findById(id);

        if (platformOptional.isEmpty()) {
            throw new PlatformNotFoundException(String.format("Platform with id [%d] not found", id));
        }

        return platformOptional.get();
    }

}
