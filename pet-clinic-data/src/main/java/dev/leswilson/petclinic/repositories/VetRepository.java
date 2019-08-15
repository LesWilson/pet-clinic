package dev.leswilson.petclinic.repositories;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VetRepository extends CrudRepository<Vet, Long> {

    Vet findByLastName(String lastName);

    List<Vet> findAllByLastNameLike(String lastName);

    List<Vet> findAllBySpecialities(Speciality speciality);
}
