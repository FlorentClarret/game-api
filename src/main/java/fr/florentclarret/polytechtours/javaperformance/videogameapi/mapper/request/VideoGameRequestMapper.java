package fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.request;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.request.VideoGameRequest;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.ParentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoGameRequestMapper extends ParentMapper<VideoGameRequest, VideoGame> {

    VideoGameRequestMapper INSTANCE = Mappers.getMapper(VideoGameRequestMapper.class);

}
