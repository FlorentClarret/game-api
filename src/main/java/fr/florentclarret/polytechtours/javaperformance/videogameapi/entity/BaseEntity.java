package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
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
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    protected Long id;

    @Version
    @Column(name = "version")
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    protected Long version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate")
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate")
    @Getter
    @Setter(AccessLevel.PRIVATE)
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
