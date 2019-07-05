package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums.RelType;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl.PublisherControllerImpl;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public final class PublisherResourceAssembler extends AbstractCustomResourceAssembler<Publisher> {

    PublisherResourceAssembler() {
        super(PublisherControllerImpl.class);
    }

    @Override
    public Resource<Publisher> toResource(final Publisher publisher, final Link selfLink) {
        final Resource<Publisher> resource = super.toResource(publisher, selfLink);
        resource.add(linkTo(methodOn(PublisherControllerImpl.class).getVideoGames(publisher.getId())).withRel(RelType.VIDEO_GAME.getName()));
        return resource;
    }
}
