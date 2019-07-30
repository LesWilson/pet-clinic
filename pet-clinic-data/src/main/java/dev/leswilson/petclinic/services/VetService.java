package dev.leswilson.petclinic.services;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Set<Pet> findByOwner(Owner owner);

    Vet save(Vet pet);

    Set<Vet> findAll();
}
