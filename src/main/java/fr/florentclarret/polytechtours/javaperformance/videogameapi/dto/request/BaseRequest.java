package fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseRequest {

    @JsonProperty("name")
    private String name;

    public BaseRequest(final String name) {
        this.name = name;
    }

    public BaseRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }
}
