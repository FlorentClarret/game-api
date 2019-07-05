package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums.RelType;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.PublisherController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.VideoGameController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public final class PublisherResourceAssembler extends AbstractCustomResourceAssembler<Publisher> {

    PublisherResourceAssembler() {
        super(PublisherController.class);
    }

    @Override
    public Resource<Publisher> toResource(final Publisher publisher) {
        final Resource<Publisher> resource = super.toResource(publisher);

        if (publisher.getVideoGameList() != null) {
            for (final VideoGame videoGame : publisher.getVideoGameList()) {
                if (videoGame != null) {
                    resource.add(linkTo(methodOn(VideoGameController.class).one(videoGame.getId())).withRel(RelType.VIDEO_GAME.getName()));
                }
            }
        }

        return resource;
    }
}
