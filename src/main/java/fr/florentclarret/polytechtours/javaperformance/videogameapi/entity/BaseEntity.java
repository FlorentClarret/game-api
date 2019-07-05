package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    @ApiParam(hidden = true)
    @JsonIgnore
    protected Long id;

    @Version
    @Column(name = "version")
    @Getter
    @Setter
    @ApiParam(hidden = true)
    protected Long version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate")
    @Getter
    @Setter
    @ApiParam(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate")
    @Getter
    @Setter
    @ApiParam(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateDate;

    @Column(name = "name")
    @Getter
    @Setter
    protected String name;

    @PrePersist
    private void onCreate() {
        this.createDate = this.updateDate = new Date();
    }

    @PreUpdate
    private void onUpdate() {
        this.updateDate = new Date();
    }

}
