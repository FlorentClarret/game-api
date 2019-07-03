package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "publisher")
public final class Publisher {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
    private List<VideoGame> videoGameList = new ArrayList<>();

    public Publisher(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }
}
