package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "video_game")
public final class VideoGame extends BaseEntity {

    @Column(name = "year")
    private Integer year;

    @Column(name = "critic_score")
    private String criticScore;

    @Column(name = "user_score")
    private String userScore;

    @Column(name = "global_sales")
    private String globalSales;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @JsonIgnore
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    @JsonIgnore
    private Platform platform;

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public String getCriticScore() {
        return criticScore;
    }

    public void setCriticScore(final String criticScore) {
        this.criticScore = criticScore;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(final String userScore) {
        this.userScore = userScore;
    }

    public String getGlobalSales() {
        return globalSales;
    }

    public void setGlobalSales(final String globalSales) {
        this.globalSales = globalSales;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(final Publisher publisher) {
        this.publisher = publisher;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }
}
