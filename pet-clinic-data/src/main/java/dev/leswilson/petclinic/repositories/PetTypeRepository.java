package dev.leswilson.petclinic.repositories;

import dev.leswilson.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
