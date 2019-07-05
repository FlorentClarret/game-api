package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.AbstractCustomResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractController<T extends BaseEntity> implements Controller<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityService<T> entityService;

    private final AbstractCustomResourceAssembler<T> resourceAssembler;

    public AbstractController(final EntityService<T> entityService, final AbstractCustomResourceAssembler<T> resourceAssembler) {
        this.entityService = entityService;
        this.resourceAssembler = resourceAssembler;
    }

    @Override
    @GetMapping
    public Resources<Resource<T>> all() {
        logger.trace("Method [getAll] called");
        return this.resourceAssembler.toResources(this.entityService.findAll());
    }

    @Override
    @GetMapping(path = "{id}")
    public Resource<T> one(@PathVariable final Long id) {
        logger.trace("Method [get] with id [{}] called", id);
        return this.resourceAssembler.toResource(this.entityService.findById(id));
    }
}
