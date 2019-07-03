package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1.0/platform/")
public class PlatformController implements Controller<Platform> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformController.class);

    private final PlatformService platformService;


    public PlatformController(final PlatformService platformService) {
        this.platformService = platformService;
    }

    @Override
    @GetMapping
    public List<Platform> all() {
        LOGGER.trace("Method [getAll] called");
        return this.platformService.findAll();
    }

    @Override
    @GetMapping(path = "{id}")
    public Platform one(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return this.platformService.findById(id);
    }
}
