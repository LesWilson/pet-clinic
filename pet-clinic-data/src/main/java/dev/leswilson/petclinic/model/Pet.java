package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
@TableGenerator(
        name="idGen",
        table="next_ids",
        pkColumnName = "table_name",
        valueColumnName = "next_id",
        pkColumnValue="pet",
        allocationSize=5,
        initialValue = 0
)
public class Pet extends BaseEntity {

    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private List<Visit> visits = new ArrayList<>();
}
