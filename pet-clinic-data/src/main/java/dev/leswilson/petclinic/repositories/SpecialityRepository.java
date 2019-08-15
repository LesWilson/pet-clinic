package dev.leswilson.petclinic.repositories;

import dev.leswilson.petclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {

}
