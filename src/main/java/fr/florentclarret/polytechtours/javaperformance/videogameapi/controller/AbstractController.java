package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.AbstractCustomResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractController<T extends BaseEntity, U extends EntityService<T>> implements Controller<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final U entityService;

    private final AbstractCustomResourceAssembler<T> resourceAssembler;

    public AbstractController(final U entityService, final AbstractCustomResourceAssembler<T> resourceAssembler) {
        this.entityService = entityService;
        this.resourceAssembler = resourceAssembler;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<T>> all() {
        logger.trace("Method [getAll] called");
        return this.resourceAssembler.toResources(this.entityService.findAll());
    }

    @Override
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<T> one(@PathVariable final Long id) {
        logger.trace("Method [get] with id [{}] called", id);
        return this.resourceAssembler.toResource(this.entityService.findById(id));
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<T>> create(@RequestBody final T entity) throws URISyntaxException {
        logger.trace("Method [create] with entity [{}] called", entity);
        final Resource<T> resource =
                resourceAssembler.toResource(this.entityService.save(entity));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @Override
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<T>> update(@PathVariable final Long id, @RequestBody final T entity) throws URISyntaxException {
        logger.trace("Method [update] with entity [{}] on id [{}] called", entity, id);
        final Resource<T> resource = this.resourceAssembler.toResource(this.entityService.update(id, entity));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @Override
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        logger.trace("Method [delete] with id [{}] called", id);
        this.entityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    protected U getEntityService() {
        return entityService;
    }
}
