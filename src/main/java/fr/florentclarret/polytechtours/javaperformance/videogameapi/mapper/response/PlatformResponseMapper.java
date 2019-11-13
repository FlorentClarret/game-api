package fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.response;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.response.PlatformResponse;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Platform;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.ParentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlatformResponseMapper extends ParentMapper<PlatformResponse, Platform> {

    PlatformResponseMapper INSTANCE = Mappers.getMapper(PlatformResponseMapper.class);

}
