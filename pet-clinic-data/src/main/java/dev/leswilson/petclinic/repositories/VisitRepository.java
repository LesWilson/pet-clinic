package dev.leswilson.petclinic.repositories;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    List<Visit> findAllByPet(Pet pet);
    List<Visit> findAllByPetOwner(Owner owner);
}
