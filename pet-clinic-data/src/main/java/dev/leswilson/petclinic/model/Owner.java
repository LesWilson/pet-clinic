package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Owner extends Person {

    private Address address;
    private String telephone;
    private Set<Pet> pets;
}
