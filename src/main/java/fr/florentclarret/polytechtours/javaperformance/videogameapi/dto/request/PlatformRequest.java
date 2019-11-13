package fr.florentclarret.polytechtours.javaperformance.videogameapi.dto.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlatformRequest extends BaseRequest {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .toString();
    }
}
