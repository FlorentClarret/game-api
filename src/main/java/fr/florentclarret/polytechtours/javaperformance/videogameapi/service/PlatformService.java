package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;

import java.util.List;

public interface PlatformService {
    List<Platform> findAll();

    Platform findById(Long id);
}
