package dev.leswilson.petclinic.services;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;

import java.util.List;

public interface VetService extends CrudService<Vet, Long> {

    List<Vet> findBySpeciality(Speciality speciality);
    Vet findByLastName(String lastName);
    List<Vet> findAllByLastNameLike(String lastName);

}
