package fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.BusinessException;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.repository.VideoGameRepository;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.AbstractEntityService;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.VideoGameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public final class VideoGameServiceImpl extends AbstractEntityService<VideoGame, VideoGameRepository> implements VideoGameService {

    private final PlatformServiceImpl platformService;

    private final PublisherServiceImpl publisherService;

    public VideoGameServiceImpl(final VideoGameRepository repository, final PlatformServiceImpl platformService, final PublisherServiceImpl publisherService) {
        super(repository);
        this.platformService = platformService;
        this.publisherService = publisherService;
    }

    @Override
    public VideoGame update(final Long id, final VideoGame entity) {
        super.repository.findByName(entity.getName()).ifPresent(s -> {
            throw new BusinessException(String.format("The entity with name [%s] already exists", entity.getName()), HttpStatus.CONFLICT);
        });

        final VideoGame oldEntity = super.repository.findById(id).orElseThrow(() -> new BusinessException(String.format("Entity with id [%d] not found", id), HttpStatus.NOT_FOUND));

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

        return super.repository.save(oldEntity);
    }

    @Override
    public List<VideoGame> getByPublisherId(final Long id) {
        final List<VideoGame> videoGames = super.repository.findByPublisherId(id);

        if (CollectionUtils.isEmpty(videoGames)) {
            throw new BusinessException(String.format("No game found for publisher with id [%s]", id), HttpStatus.NOT_FOUND);
        }

        return videoGames;
    }

    @Override
    public List<VideoGame> getByPlatformId(final Long id) {
        final List<VideoGame> videoGames = super.repository.findByPlatformId(id);

        if (CollectionUtils.isEmpty(videoGames)) {
            throw new BusinessException(String.format("No game found for platform with id [%s]", id), HttpStatus.NOT_FOUND);
        }

        return videoGames;
    }

    @Override
    public Publisher getPublisherForGameWithId(final Long gameId) {
        final VideoGame videoGame = this.findById(gameId);

        if (videoGame.getPublisher() != null) {
            return videoGame.getPublisher();
        } else {
            throw new BusinessException(String.format("No publisher found for game with id [%s]", gameId), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Platform getPlatformForGameWithId(final Long gameId) {
        final VideoGame videoGame = this.findById(gameId);

        if (videoGame.getPlatform() != null) {
            return videoGame.getPlatform();
        } else {
            throw new BusinessException(String.format("No platform found for game with id [%s]", gameId), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void removePublisher(final Long gameId) {
        final VideoGame videoGame = this.findById(gameId);
        videoGame.setPublisher(null);
        this.repository.save(videoGame);
    }

    @Override
    public void removePlatform(final Long gameId) {
        final VideoGame videoGame = this.findById(gameId);
        videoGame.setPlatform(null);
        this.repository.save(videoGame);
    }

    @Override
    public Publisher setPublisher(final Long gameId, final Publisher publisher) {
        final VideoGame videoGame = this.findById(gameId);

        try {
            videoGame.setPublisher(this.publisherService.findByName(publisher.getName()));
        } catch (final BusinessException e) {
            videoGame.setPublisher(this.publisherService.save(publisher));
        }

        this.repository.save(videoGame);
        return videoGame.getPublisher();
    }

    @Override
    public Platform setPlatform(final Long gameId, final Platform platform) {
        final VideoGame videoGame = this.findById(gameId);

        try {
            videoGame.setPlatform(this.platformService.findByName(platform.getName()));
        } catch (final BusinessException e) {
            videoGame.setPlatform(this.platformService.save(platform));
        }

        this.repository.save(videoGame);
        return videoGame.getPlatform();
    }
}
