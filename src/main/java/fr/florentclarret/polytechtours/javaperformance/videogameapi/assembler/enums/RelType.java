package fr.florentclarret.polytechtours.javaperformance.videogameapi.assembler.enums;

public enum RelType {
    VIDEO_GAME("videogame"),
    PUBLISHER("publisher"),
    PLATFORM("platform");

    private final String name;

    RelType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}