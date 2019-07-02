package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.assembler.PublisherResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.PublisherService;
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
@RequestMapping(path = "/api/v1.0/publisher/")
public final class PublisherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherController.class);

    private final PublisherService publisherService;

    private final PublisherResourceAssembler publisherResourceAssembler;

    public PublisherController(final PublisherService publisherService, final PublisherResourceAssembler publisherResourceAssembler) {
        this.publisherService = publisherService;
        this.publisherResourceAssembler = publisherResourceAssembler;
    }

    @GetMapping
    public Resources<Resource<Publisher>> all() {
        LOGGER.trace("Method [getAll] called");

        final List<Resource<Publisher>> platforms = this.publisherService.findAll().stream()
                .map(this.publisherResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(platforms,
                linkTo(methodOn(PlatformController.class).all()).withSelfRel());
    }

    @GetMapping(path = "{id}")
    public Resource<Publisher> one(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return this.publisherResourceAssembler.toResource(this.publisherService.findById(id));
    }
}
