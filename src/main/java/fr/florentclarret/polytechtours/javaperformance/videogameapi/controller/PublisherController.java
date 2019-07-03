package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1.0/publisher/")
public class PublisherController implements Controller<Publisher> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherController.class);

    private final PublisherService publisherService;

    public PublisherController(final PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Override
    @GetMapping
    public List<Publisher> all() {
        LOGGER.trace("Method [getAll] called");
        return this.publisherService.findAll();
    }

    @Override
    @GetMapping(path = "{id}")
    public Publisher one(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return this.publisherService.findById(id);
    }
}
