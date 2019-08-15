package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@TableGenerator(
        name="idGen",
        table="next_ids",
        pkColumnName = "table_name",
        valueColumnName = "next_id",
        pkColumnValue="pet_type",
        allocationSize=5,
        initialValue = 1
)
public class PetType extends BaseEntity {
    private String name;
}
