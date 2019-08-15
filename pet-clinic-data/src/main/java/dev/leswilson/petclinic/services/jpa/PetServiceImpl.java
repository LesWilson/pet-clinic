package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.repositories.PetRepository;
import dev.leswilson.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("jpa")
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> specialities = new HashSet<>();
        petRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public void delete(Pet t) {
        petRepository.delete(t);
    }

    @Override
    public Pet save(Pet pet) {
        // add logic to iterate through pets and save any that don't exist
        if(pet != null) {
            return petRepository.save(pet);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pet> findByOwner(Owner owner) {
        return petRepository.findAllByOwner(owner);
    }
}
