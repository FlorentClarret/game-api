package fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class VideoGameRequest extends BaseRequest {

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("critic_score")
    private String criticScore;

    @JsonProperty("user_score")
    private String userScore;

    @JsonProperty("global_sales")
    private String globalSales;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("year", year)
                .append("criticScore", criticScore)
                .append("userScore", userScore)
                .append("globalSales", globalSales)
                .toString();
    }
}
