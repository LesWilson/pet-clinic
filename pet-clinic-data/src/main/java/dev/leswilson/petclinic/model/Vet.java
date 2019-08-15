package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"specialities"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
@TableGenerator(
        name="idGen",
        table="next_ids",
        pkColumnName = "table_name",
        valueColumnName = "next_id",
        pkColumnValue="vet",
        allocationSize=5,
        initialValue = 0
)
public class Vet extends Person {

    @ManyToMany
    @JoinTable(name = "vet_speciality",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private List<Speciality> specialities = new ArrayList<>();

}
