package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;

//    public Person(Long id, String createdBy, Date createdAt, String updatedBy, Date updatedAt, String firstName, String lastName) {
//        super(id, createdBy, createdAt, updatedBy, updatedAt);
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
}
