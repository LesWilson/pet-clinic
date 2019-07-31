package dev.leswilson.petclinic.services;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;

import java.util.Set;

public interface PetService extends CrudService<Pet, Long> {

    Set<Pet> findByOwner(Owner owner);

}
