package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.VideoGameNotFoundException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.VideoGameRepository;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.VideoGameService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public final class VideoGameServiceImpl implements VideoGameService {

    private final VideoGameRepository videoGameRepository;

    public VideoGameServiceImpl(final VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    @Override
    public List<VideoGame> findAll() {
        final List<VideoGame> platforms = this.videoGameRepository.findAll();

        if (CollectionUtils.isEmpty(platforms)) {
            throw new VideoGameNotFoundException("No video games found");
        }

        return platforms;
    }

    @Override
    public VideoGame findById(final Long id) {
        final Optional<VideoGame> platformOptional = this.videoGameRepository.findById(id);

        if (platformOptional.isEmpty()) {
            throw new VideoGameNotFoundException(String.format("Video game with id [%d] not found", id));
        }

        return platformOptional.get();
    }
}
