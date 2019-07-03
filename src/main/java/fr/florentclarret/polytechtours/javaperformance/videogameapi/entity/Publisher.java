package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(name = "publisher")
public final class Publisher extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
    private List<VideoGame> videoGameList = new ArrayList<>();

    public Publisher(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }
}
