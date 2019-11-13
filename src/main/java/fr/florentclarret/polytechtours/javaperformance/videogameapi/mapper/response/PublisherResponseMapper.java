package fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.response;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.response.PublisherResponse;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.ParentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherResponseMapper extends ParentMapper<PublisherResponse, Publisher> {

    PublisherResponseMapper INSTANCE = Mappers.getMapper(PublisherResponseMapper.class);

}
