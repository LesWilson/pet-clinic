package dev.leswilson.petclinic.services;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Visit;

import java.util.Set;

public interface VisitService extends CrudService<Visit, Long> {

    Set<Visit> findByPet(Pet pet);

    Set<Visit> findByOwner(Owner owner);

}
