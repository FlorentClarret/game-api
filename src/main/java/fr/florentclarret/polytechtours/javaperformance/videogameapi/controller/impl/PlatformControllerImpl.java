package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.PlatformResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.AbstractController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl.PlatformServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1.0/platform/")
public class PlatformControllerImpl extends AbstractController<Platform> {

    public PlatformControllerImpl(final PlatformServiceImpl platformService, final PlatformResourceAssembler resourceAssembler) {
        super(platformService, resourceAssembler);
    }
}
