package fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlatformResponse extends BaseResponse {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .toString();
    }

}
