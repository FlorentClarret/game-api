package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.assembler.VideoGameResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.VideoGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/v1.0/videogame/")
public class VideoGameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoGameController.class);

    private final VideoGameService videoGameService;

    private final VideoGameResourceAssembler videoGameResourceAssembler;

    public VideoGameController(final VideoGameService videoGameService, final VideoGameResourceAssembler videoGameResourceAssembler) {
        this.videoGameService = videoGameService;
        this.videoGameResourceAssembler = videoGameResourceAssembler;
    }

    @GetMapping
    public Resources<Resource<VideoGame>> all() {
        LOGGER.trace("Method [getAll] called");

        final List<Resource<VideoGame>> platforms = this.videoGameService.findAll().stream()
                .map(this.videoGameResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(platforms,
                linkTo(methodOn(PlatformController.class).all()).withSelfRel());
    }

    @GetMapping(path = "{id}")
    public Resource<VideoGame> one(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return this.videoGameResourceAssembler.toResource(this.videoGameService.findById(id));
    }
}
