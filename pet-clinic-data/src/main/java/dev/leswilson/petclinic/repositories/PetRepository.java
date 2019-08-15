package dev.leswilson.petclinic.repositories;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {

    List<Pet> findAllByOwner(Owner owner);
}
