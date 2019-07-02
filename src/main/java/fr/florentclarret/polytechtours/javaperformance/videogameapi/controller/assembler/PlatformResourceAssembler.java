package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.PlatformController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public final class PlatformResourceAssembler implements ResourceAssembler<Platform, Resource<Platform>> {

    @Override
    public Resource<Platform> toResource(final Platform platform) {
        return new Resource<>(platform,
                linkTo(methodOn(PlatformController.class).one(platform.getId())).withSelfRel(),
                linkTo(methodOn(PlatformController.class).all()).withRel("platform"));
    }

}
