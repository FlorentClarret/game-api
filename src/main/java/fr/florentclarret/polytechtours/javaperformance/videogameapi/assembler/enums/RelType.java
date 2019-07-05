package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RelType {
    VIDEO_GAME("videogame"),
    PUBLISHER("publisher"),
    PLATFORM("platform");

    private final String name;

}