package fr.florentclarret.polytechtours.javaperformance.videogameapi.service;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> findAll();

    Publisher findById(Long id);
}
