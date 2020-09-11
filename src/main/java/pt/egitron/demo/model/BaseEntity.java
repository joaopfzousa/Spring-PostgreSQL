package pt.egitron.demo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    @Setter(AccessLevel.NONE)
    @Column(name = "status", nullable = false)
    private Short status = 1;

    @Version
    private short version; //Hibernate will automatically use the version number to verify that the object used in the transition has been updated since the last time it was requested.

    @CreationTimestamp
    protected LocalDateTime creationDate;

    @UpdateTimestamp
    protected LocalDateTime lastModifiedDate;


}