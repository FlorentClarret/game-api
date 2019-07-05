package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.VideoGameResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl.VideoGameServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1.0/videogame/")
public class VideoGameController implements Controller<VideoGame> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoGameController.class);

    private final VideoGameServiceImpl videoGameService;

    private final VideoGameResourceAssembler videoGameResourceAssembler;

    public VideoGameController(final VideoGameServiceImpl videoGameService, final VideoGameResourceAssembler videoGameResourceAssembler) {
        this.videoGameService = videoGameService;
        this.videoGameResourceAssembler = videoGameResourceAssembler;
    }

    @Override
    @GetMapping
    public Resources<Resource<VideoGame>> all() {
        LOGGER.trace("Method [getAll] called");
        return this.videoGameResourceAssembler.toResources(this.videoGameService.findAll());
    }

    @Override
    @GetMapping(path = "{id}")
    public Resource<VideoGame> one(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return this.videoGameResourceAssembler.toResource(this.videoGameService.findById(id));
    }
}
