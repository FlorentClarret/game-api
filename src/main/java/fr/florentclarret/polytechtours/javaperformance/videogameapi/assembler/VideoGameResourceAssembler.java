package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums.RelType;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl.PlatformControllerImpl;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl.PublisherControllerImpl;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl.VideoGameControllerImpl;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public final class VideoGameResourceAssembler extends AbstractCustomResourceAssembler<VideoGame> {

    VideoGameResourceAssembler() {
        super(VideoGameControllerImpl.class);
    }

    @Override
    public Resource<VideoGame> toResource(final VideoGame videoGame) {
        final Resource<VideoGame> resource = super.toResource(videoGame);

        if (videoGame.getPlatform() != null) {
            resource.add(linkTo(methodOn(PlatformControllerImpl.class).one(videoGame.getPlatform().getId())).withRel(RelType.PLATFORM.getName()));
        }

        if (videoGame.getPublisher() != null) {
            resource.add(linkTo(methodOn(PublisherControllerImpl.class).one(videoGame.getPublisher().getId())).withRel(RelType.PUBLISHER.getName()));
        }

        return resource;
    }
}
