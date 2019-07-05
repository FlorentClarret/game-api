package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums.RelType;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl.PlatformControllerImpl;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl.VideoGameControllerImpl;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public final class PlatformResourceAssembler extends AbstractCustomResourceAssembler<Platform> {

    public PlatformResourceAssembler() {
        super(PlatformControllerImpl.class);
    }

    @Override
    public Resource<Platform> toResource(final Platform platform) {
        final Resource<Platform> resource = super.toResource(platform);

        if (platform.getVideoGameList() != null) {
            for (final VideoGame videoGame : platform.getVideoGameList()) {
                if (videoGame != null) {
                    resource.add(linkTo(methodOn(VideoGameControllerImpl.class).one(videoGame.getId())).withRel(RelType.VIDEO_GAME.getName()));
                }
            }
        }

        return resource;
    }
}
