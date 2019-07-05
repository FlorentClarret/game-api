package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.PublisherResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl.PublisherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1.0/publisher/")
public class PublisherController implements Controller<Publisher> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherController.class);

    private final PublisherServiceImpl publisherService;

    private final PublisherResourceAssembler publisherResourceAssembler;

    public PublisherController(final PublisherServiceImpl publisherService, final PublisherResourceAssembler publisherResourceAssembler) {
        this.publisherService = publisherService;
        this.publisherResourceAssembler = publisherResourceAssembler;
    }

    @Override
    @GetMapping
    public Resources<Resource<Publisher>> all() {
        LOGGER.trace("Method [getAll] called");
        return this.publisherResourceAssembler.toResources(this.publisherService.findAll());
    }

    @Override
    @GetMapping(path = "{id}")
    public Resource<Publisher> one(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return this.publisherResourceAssembler.toResource(this.publisherService.findById(id));
    }
}
