package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.PetType;
import dev.leswilson.petclinic.repositories.PetTypeRepository;
import dev.leswilson.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class PetTypeServiceImpl implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> specialities = new HashSet<>();
        petTypeRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public void delete(PetType t) {
        petTypeRepository.delete(t);
    }

    @Override
    public PetType save(PetType petType) {
        // add logic to iterate through pets and save any that don't exist
        if(petType != null) {
            return petTypeRepository.save(petType);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

}
