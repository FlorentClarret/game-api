package fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class BaseResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("create_date")
    private Date createDate;

    @JsonProperty("update_date")
    private Date updateDate;

    public BaseResponse(final String name, final Date createDate, final Date updateDate) {
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public BaseResponse() {
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
                .appendSuper(super.toString())
                .append("name", name)
                .append("createDate", createDate)
                .append("updateDate", updateDate)
                .toString();
    }
}
