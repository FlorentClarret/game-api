package fr.florentclarret.polytechtours.javaperformance.videogameapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    @Column(name = "id")
    @Getter
    @Setter
    protected Long id;

    @Version
    @Column(name = "version")
    @Getter
    @Setter
    protected Long version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate")
    @Getter
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate")
    @Getter
    private Date updateDate;

    @PrePersist
    protected void onCreate() {
        this.createDate = this.updateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = new Date();
    }

}
