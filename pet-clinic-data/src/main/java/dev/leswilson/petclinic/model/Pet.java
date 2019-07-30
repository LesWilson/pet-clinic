package dev.leswilson.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Pet {

    private LocalDate birthDate;
    private Owner owner;
    private PetType petType;
}
