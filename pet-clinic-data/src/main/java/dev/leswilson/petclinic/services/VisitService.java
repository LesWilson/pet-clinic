package dev.leswilson.petclinic.services;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Visit;

import java.util.List;

public interface VisitService extends CrudService<Visit, Long> {

    List<Visit> findByPet(Pet pet);

    List<Visit> findByOwner(Owner owner);

}
