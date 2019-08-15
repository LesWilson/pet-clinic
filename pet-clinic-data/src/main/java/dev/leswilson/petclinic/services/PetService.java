package dev.leswilson.petclinic.services;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;

import java.util.List;

public interface PetService extends CrudService<Pet, Long> {

    List<Pet> findByOwner(Owner owner);

}
