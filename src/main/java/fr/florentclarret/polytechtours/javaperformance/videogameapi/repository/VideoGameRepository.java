package fr.florentclarret.polytechtours.javaperformance.videogameapi.repository;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;

import java.util.List;

public interface VideoGameRepository extends BaseEntityRepository<VideoGame> {

    List<VideoGame> findByPublisherId(final Long id);

    List<VideoGame> findByPlatformId(final Long id);
}
