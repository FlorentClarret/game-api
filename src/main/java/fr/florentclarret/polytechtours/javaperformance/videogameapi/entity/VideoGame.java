package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(name = "video_game")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = VideoGame.class)
public final class VideoGame extends BaseEntity {

    @Column(name = "year")
    private int year;

    @Column(name = "critic_score")
    private String criticScore;

    @Column(name = "user_score")
    private String userScore;

    @Column(name = "global_sales")
    private String globalSales;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

}
