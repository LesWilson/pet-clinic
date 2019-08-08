package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity {

    private LocalDate date = LocalDate.now();
    private Pet pet;
    private String description;

}
