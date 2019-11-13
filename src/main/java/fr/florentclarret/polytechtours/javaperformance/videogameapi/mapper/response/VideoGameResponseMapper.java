package fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.response;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.response.VideoGameResponse;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.VideoGame;
import fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper.ParentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoGameResponseMapper extends ParentMapper<VideoGameResponse, VideoGame> {

    VideoGameResponseMapper INSTANCE = Mappers.getMapper(VideoGameResponseMapper.class);

}
