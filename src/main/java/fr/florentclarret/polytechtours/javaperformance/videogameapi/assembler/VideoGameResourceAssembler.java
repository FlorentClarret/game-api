package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums.RelType;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.PlatformController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.PublisherController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.VideoGameController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public final class VideoGameResourceAssembler extends AbstractCustomResourceAssembler<VideoGame> {

    VideoGameResourceAssembler() {
        super(VideoGameController.class);
    }

    @Override
    public Resource<VideoGame> toResource(final VideoGame videoGame) {
        final Resource<VideoGame> resource = super.toResource(videoGame);

        if (videoGame.getPlatform() != null) {
            resource.add(linkTo(methodOn(PlatformController.class).one(videoGame.getPlatform().getId())).withRel(RelType.PLATFORM.getName()));
        }

        if (videoGame.getPublisher() != null) {
            resource.add(linkTo(methodOn(PublisherController.class).one(videoGame.getPublisher().getId())).withRel(RelType.PUBLISHER.getName()));
        }

        return resource;
    }
}
