package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.PlatformController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public final class VideoGameResourceAssembler implements ResourceAssembler<VideoGame, Resource<VideoGame>> {

    @Override
    public Resource<VideoGame> toResource(final VideoGame videoGame) {
        return new Resource<>(videoGame,
                linkTo(methodOn(PlatformController.class).one(videoGame.getId())).withSelfRel(),
                linkTo(methodOn(PlatformController.class).all()).withRel("videoGame"));
    }

}
