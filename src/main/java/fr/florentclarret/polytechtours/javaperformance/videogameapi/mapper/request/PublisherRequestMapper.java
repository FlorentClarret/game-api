package fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.request;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.request.PublisherRequest;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.ParentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherRequestMapper extends ParentMapper<PublisherRequest, Publisher> {

    PublisherRequestMapper INSTANCE = Mappers.getMapper(PublisherRequestMapper.class);

}
