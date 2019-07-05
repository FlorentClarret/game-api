package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Setter
@RequiredArgsConstructor
@ToString
@Table(name = "video_game")
public final class VideoGame extends BaseEntity {

    @Column(name = "year")
    @Getter
    private Integer year;

    @Column(name = "critic_score")
    @Getter
    private String criticScore;

    @Column(name = "user_score")
    @Getter
    private String userScore;

    @Column(name = "global_sales")
    @Getter
    private String globalSales;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @JsonIgnore
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    @JsonIgnore
    private Platform platform;

    @ApiModelProperty(hidden = true)
    public Publisher getPublisher() {
        return publisher;
    }

    @ApiModelProperty(hidden = true)
    public Platform getPlatform() {
        return platform;
    }
}
