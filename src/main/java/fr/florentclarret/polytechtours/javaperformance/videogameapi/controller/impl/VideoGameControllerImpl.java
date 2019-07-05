package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.VideoGameResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.AbstractController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl.VideoGameServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1.0/videogame/")
public class VideoGameControllerImpl extends AbstractController<VideoGame> {

    public VideoGameControllerImpl(final VideoGameServiceImpl service, final VideoGameResourceAssembler resourceAssembler) {
        super(service, resourceAssembler);
    }

}
