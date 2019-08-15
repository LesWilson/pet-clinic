package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"vets"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
@TableGenerator(
        name="idGen",
        table="next_ids",
        pkColumnName = "table_name",
        valueColumnName = "next_id",
        pkColumnValue="speciality",
        allocationSize=5,
        initialValue = 0
)
public class Speciality extends BaseEntity {

    @ManyToMany(mappedBy = "specialities")
    private List<Vet> vets;

    private String description;
}
