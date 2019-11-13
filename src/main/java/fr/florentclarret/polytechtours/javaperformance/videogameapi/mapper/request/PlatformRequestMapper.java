package fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.request;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.request.PlatformRequest;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.ParentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlatformRequestMapper extends ParentMapper<PlatformRequest, Platform> {

    PlatformRequestMapper INSTANCE = Mappers.getMapper(PlatformRequestMapper.class);

}
