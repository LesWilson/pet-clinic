package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"pet"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
@TableGenerator(
        name="idGen",
        table="next_ids",
        pkColumnName = "table_name",
        valueColumnName = "next_id",
        pkColumnValue="visit",
        allocationSize=5
)
public class Visit extends BaseEntity {

    /* Sonar Ignore Start */
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private String description;
    /* Sonar Ignore End */

}
