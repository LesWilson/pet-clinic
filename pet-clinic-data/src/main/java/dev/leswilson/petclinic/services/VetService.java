package dev.leswilson.petclinic.services;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;

import java.util.Set;

public interface VetService extends CrudService<Vet, Long> {

    Set<Vet> findBySpeciality(Speciality speciality);
}
