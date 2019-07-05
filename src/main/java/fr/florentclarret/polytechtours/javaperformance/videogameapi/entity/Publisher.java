package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
@RequiredArgsConstructor
@Setter
@ToString
@Table(name = "publisher")
public final class Publisher extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
    @JsonIgnore
    private List<VideoGame> videoGameList = new ArrayList<>();

    @ApiModelProperty(hidden = true)
    public List<VideoGame> getVideoGameList() {
        return videoGameList;
    }
}
