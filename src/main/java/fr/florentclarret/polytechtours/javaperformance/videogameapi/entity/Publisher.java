package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publisher")
public final class Publisher extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private List<VideoGame> videoGameList = new ArrayList<>();

    public List<VideoGame> getVideoGameList() {
        return videoGameList;
    }

    public void setVideoGameList(final List<VideoGame> videoGameList) {
        this.videoGameList = videoGameList;
    }
}
