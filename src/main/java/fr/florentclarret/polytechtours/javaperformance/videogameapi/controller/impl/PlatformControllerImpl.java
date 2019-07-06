package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.PlatformResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.VideoGameResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.AbstractController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl.PlatformServiceImpl;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl.VideoGameServiceImpl;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/v1.0/platform/")
public class PlatformControllerImpl extends AbstractController<Platform> {

    private final VideoGameResourceAssembler videoGameResourceAssembler;

    private final VideoGameServiceImpl videoGameService;

    public PlatformControllerImpl(final PlatformServiceImpl platformService, final PlatformResourceAssembler resourceAssembler, final VideoGameResourceAssembler videoGameResourceAssembler, final VideoGameServiceImpl videoGameService) {
        super(platformService, resourceAssembler);
        this.videoGameResourceAssembler = videoGameResourceAssembler;
        this.videoGameService = videoGameService;
    }

    @GetMapping(path = "{id}/videogame", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<VideoGame>> getVideoGames(@PathVariable final Long id) {
        return this.videoGameResourceAssembler.toResources(this.videoGameService.getByPlatformId(id), linkTo(methodOn(this.getClass()).getVideoGames(id)).withSelfRel());
    }
}
