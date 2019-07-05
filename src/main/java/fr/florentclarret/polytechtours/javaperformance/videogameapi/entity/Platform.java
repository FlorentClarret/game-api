package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@ToString
@Table(name = "platform")
public final class Platform extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "platform")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private List<VideoGame> videoGameList;
}
