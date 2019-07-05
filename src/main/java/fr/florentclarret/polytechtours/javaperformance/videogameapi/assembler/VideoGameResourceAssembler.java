package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums.RelType;
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
        resource.add(linkTo(methodOn(VideoGameControllerImpl.class).getPlatform(videoGame.getId())).withRel(RelType.PLATFORM.getName()));
        resource.add(linkTo(methodOn(VideoGameControllerImpl.class).getPublisher(videoGame.getId())).withRel(RelType.PUBLISHER.getName()));
        return resource;
    }
}
