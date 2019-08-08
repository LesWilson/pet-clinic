package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity {

    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.of(12, 30);
    private Pet pet;
    private String description;

}
