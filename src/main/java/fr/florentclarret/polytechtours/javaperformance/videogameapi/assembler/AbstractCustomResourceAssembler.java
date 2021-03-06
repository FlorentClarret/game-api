package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.Controller;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public abstract class AbstractCustomResourceAssembler<T extends BaseEntity> implements ResourceAssembler<T, Resource<T>> {

    private final Class<? extends Controller<T>> controllerClass;

    AbstractCustomResourceAssembler(final Class<? extends Controller<T>> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Resources<Resource<T>> toResources(final Collection<T> entities) {
        return this.toResources(entities, linkTo(methodOn(controllerClass).all()).withSelfRel());
    }

    public Resources<Resource<T>> toResources(final Collection<T> entities, final Link selfLink) {
        final Collection<Resource<T>> resources = entities.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources, selfLink);
    }

    @Override
    public Resource<T> toResource(final T entity) {
        return this.toResource(entity, linkTo(methodOn(controllerClass).one(entity.getId())).withSelfRel());
    }

    public Resource<T> toResource(final T entity, final Link selfLink) {
        return new Resource<>(entity, selfLink, linkTo(methodOn(controllerClass).all()).withRel("all"));
    }

}
