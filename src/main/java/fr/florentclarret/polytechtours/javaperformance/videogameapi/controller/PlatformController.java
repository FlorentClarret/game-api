package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.assembler.PlatformResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.PlatformService;
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
@RequestMapping(path = "/api/v1.0/platform/")
public class PlatformController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformController.class);

    private final PlatformService platformService;

    private final PlatformResourceAssembler platformResourceAssembler;

    public PlatformController(final PlatformService platformService, final PlatformResourceAssembler platformResourceAssembler) {
        this.platformService = platformService;
        this.platformResourceAssembler = platformResourceAssembler;
    }

    @GetMapping
    public Resources<Resource<Platform>> all() {
        LOGGER.trace("Method [getAll] called");

        final List<Resource<Platform>> platforms = this.platformService.findAll().stream()
                .map(this.platformResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(platforms,
                linkTo(methodOn(PlatformController.class).all()).withSelfRel());
    }

    @GetMapping(path = "{id}")
    public Resource<Platform> one(@PathVariable final Long id) {
        LOGGER.trace("Method [get] with id [{}] called", id);
        return this.platformResourceAssembler.toResource(this.platformService.findById(id));
    }
}
