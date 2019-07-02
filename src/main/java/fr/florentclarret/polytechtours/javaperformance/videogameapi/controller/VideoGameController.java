package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.VideoGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1.0/videogames/")
public final class VideoGameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoGameController.class);

    private final VideoGameService videoGameService;

    public VideoGameController(final VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GetMapping
    public String getAll() {
        LOGGER.trace("Method [getAll] called");
        return "getAll";
    }

    @GetMapping(path = "{id}")
    public String get(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return "get " + id;
    }
}
