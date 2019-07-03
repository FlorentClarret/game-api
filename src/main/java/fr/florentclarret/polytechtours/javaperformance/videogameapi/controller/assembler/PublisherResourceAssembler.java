package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.PublisherController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public final class PublisherResourceAssembler implements ResourceAssembler<Publisher, Resource<Publisher>> {

    @Override
    public Resource<Publisher> toResource(final Publisher publisher) {
        return new Resource<>(publisher,
                linkTo(methodOn(PublisherController.class).one(publisher.getId())).withSelfRel(),
                linkTo(methodOn(PublisherController.class).all()).withRel("publisher"));
    }

}
