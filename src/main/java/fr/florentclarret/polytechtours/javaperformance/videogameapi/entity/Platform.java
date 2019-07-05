package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Table(name = "platform")
public final class Platform extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "platform")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<VideoGame> videoGameList = new ArrayList<>();
}
