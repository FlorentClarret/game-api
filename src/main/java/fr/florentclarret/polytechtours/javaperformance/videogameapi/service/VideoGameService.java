package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;

import java.util.List;

public interface VideoGameService {
    List<VideoGame> findAll();

    VideoGame findById(Long id);
}
