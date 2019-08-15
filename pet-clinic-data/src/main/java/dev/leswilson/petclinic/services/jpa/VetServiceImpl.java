package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.repositories.VetRepository;
import dev.leswilson.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("jpa")
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public void delete(Vet t) {
        vetRepository.delete(t);
    }

    @Override
    public Vet save(Vet vet) {
        // add logic to iterate through pets and save any that don't exist
        if(vet != null) {
            return vetRepository.save(vet);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vet> findBySpeciality(Speciality speciality) {
        return vetRepository.findAllBySpecialities(speciality);
    }
}
