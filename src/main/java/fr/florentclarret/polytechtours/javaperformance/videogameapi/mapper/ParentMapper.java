package fr.florentclarret.polytechtours.javaperformance.videogameapi.mapper;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ParentMapper<DtoObject, EntityObject extends BaseEntity> {

    DtoObject fromEntityToDto(EntityObject entityObject);

    EntityObject fromDtoToEntity(DtoObject dtoObject);

    default List<DtoObject> fromEntityToDto(final List<EntityObject> entityObjects) {
        if (entityObjects == null) {
            return new ArrayList<>();
        }

        return entityObjects.stream().map(this::fromEntityToDto).collect(Collectors.toList());
    }

    default List<EntityObject> fromDtoToEntity(final List<DtoObject> dtoObjects) {
        if (dtoObjects == null) {
            return new ArrayList<>();
        }

        return dtoObjects.stream().map(this::fromDtoToEntity).collect(Collectors.toList());
    }
}
