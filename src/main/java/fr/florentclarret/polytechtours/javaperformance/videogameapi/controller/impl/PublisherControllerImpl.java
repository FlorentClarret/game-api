package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.impl;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.PublisherResourceAssembler;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.AbstractController;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.service.impl.PublisherServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1.0/publisher/")
public class PublisherControllerImpl extends AbstractController<Publisher> {

    public PublisherControllerImpl(final PublisherServiceImpl service, final PublisherResourceAssembler resourceAssembler) {
        super(service, resourceAssembler);
    }
}
