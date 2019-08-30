package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
//@Builder
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity implements Serializable {

    /* Sonar Ignore Start */
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="idGen")
    private Long id;

    @CreatedBy
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate   // can also use @CreationTimestamp
    private Date createdAt;

    @LastModifiedBy
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @LastModifiedDate   // can also use @UpdateTimestamp
    private Date updatedAt;
    /* Sonar Ignore End */

    public boolean isNew() {
        return id == null;
    }
}
