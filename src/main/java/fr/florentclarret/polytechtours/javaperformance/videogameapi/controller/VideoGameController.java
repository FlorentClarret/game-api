package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.VideoGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1.0/videogame/")
public class VideoGameController implements Controller<VideoGame> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoGameController.class);

    private final VideoGameService videoGameService;

    public VideoGameController(final VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @Override
    @GetMapping
    public List<VideoGame> all() {
        LOGGER.trace("Method [getAll] called");
        return this.videoGameService.findAll();
    }

    @Override
    @GetMapping(path = "{id}")
    public VideoGame one(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return this.videoGameService.findById(id);
    }
}
