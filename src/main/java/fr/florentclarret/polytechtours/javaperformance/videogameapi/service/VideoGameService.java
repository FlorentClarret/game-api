package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;

import java.util.List;

public interface VideoGameService extends EntityService<VideoGame> {

    List<VideoGame> getByPublisherId(Long id);

    List<VideoGame> getByPlatformId(Long id);

    Publisher getPublisherForGameWithId(Long gameId);

    Platform getPlatformForGameWithId(Long gameId);

    void removePublisher(Long gameId);

    void removePlatform(Long gameId);
}
