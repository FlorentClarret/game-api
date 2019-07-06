package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.PlatformResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.PublisherResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.VideoGameResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.AbstractController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.VideoGameService;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/v1.0/videogame/")
public class VideoGameControllerImpl extends AbstractController<VideoGame, VideoGameService> {

    private final PublisherResourceAssembler publisherResourceAssembler;

    private final PlatformResourceAssembler platformResourceAssembler;

    public VideoGameControllerImpl(final VideoGameService service, final VideoGameResourceAssembler resourceAssembler, final PublisherResourceAssembler publisherResourceAssembler, final PlatformResourceAssembler platformResourceAssembler) {
        super(service, resourceAssembler);
        this.publisherResourceAssembler = publisherResourceAssembler;
        this.platformResourceAssembler = platformResourceAssembler;
    }

    @GetMapping(path = "{id}/publisher", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Publisher> getPublisher(@PathVariable final Long id) {
        return this.publisherResourceAssembler.toResource(super.entityService.getPublisherForGameWithId(id), linkTo(methodOn(this.getClass()).getPublisher(id)).withSelfRel());
    }

    @DeleteMapping(path = "{id}/publisher")
    public ResponseEntity<Void> deletePublisher(@PathVariable final Long id) {
        super.entityService.removePublisher(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "{id}/publisher")
    public Resource<Publisher> setPublisher(@PathVariable final Long id, @RequestBody final Publisher publisher) {
        return this.publisherResourceAssembler.toResource(super.entityService.setPublisher(id, publisher), linkTo(methodOn(this.getClass()).getPublisher(id)).withSelfRel());
    }

    @GetMapping(path = "{id}/platform", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Platform> getPlatform(@PathVariable final Long id) {
        return this.platformResourceAssembler.toResource(super.entityService.getPlatformForGameWithId(id), linkTo(methodOn(this.getClass()).getPlatform(id)).withSelfRel());
    }

    @DeleteMapping(path = "{id}/platform")
    public ResponseEntity<Void> deletePlatform(@PathVariable final Long id) {
        super.entityService.removePlatform(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "{id}/platform")
    public Resource<Platform> setPlatform(@PathVariable final Long id, @RequestBody final Platform platform) {
        return this.platformResourceAssembler.toResource(super.entityService.setPlatform(id, platform), linkTo(methodOn(this.getClass()).getPlatform(id)).withSelfRel());
    }

}
